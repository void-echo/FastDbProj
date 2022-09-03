package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.MyWebSocketHandler;
import com.echo.fastdbproj.entity.*;
import com.echo.fastdbproj.service.*;
import com.echo.fastdbproj.util.BinUtils;
import com.echo.fastdbproj.util.UnitedLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

import static java.lang.Thread.sleep;

/**
 * Message Format:
 */

@RestController
@CrossOrigin
@RequestMapping("running")
public class MainController {
    MainService mainService;
    MyWebSocketHandler handler;
    DriverService driverService;
    CustomerService customerService;

    ReserveBillService reserveBillService;
    DisputeService disputeService;

    CarService carService;

    BillService billService;
    Map<String, AtomicBoolean> handledMap = new HashMap<>(); // map preBillId to isCaught

    Map<String, Bill> billMap = new HashMap<>();
    Map<String, AtomicBoolean> handleMap_YYK = new HashMap<>();
    Executor exe;

    BinUtils binUtils;

    @Autowired
    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
    }

    @RequestMapping("bind-car-to-driver")
    public ResponseEntity<Boolean> bindCar2driver(String driverId, String carId) {
        try {
            Driver driver = driverService.queryById(driverId);
            driver.setCarId(carId);
            driverService.update(driver);
        } catch (Exception e) {
            return ResponseEntity.ok(false);
        }
        return ResponseEntity.ok(true);

    }


    @RequestMapping("sign-up-new-car")
    public ResponseEntity<Boolean> signUp4newCar(String driverId, String carId, String band, int max_speed, String carType) {
        Car car = new Car();
        car.setCarType(carType);
        car.setMaxSpeed(max_speed);
        car.setId(carId);
        car.setBand(band);
        car.setStartUsingTime(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")));
        exe.execute(() -> {
            carService.insert(car);
            Driver driver = driverService.queryById(driverId);
            driver.setCarId(carId);
            driverService.update(driver);
        });
        return ResponseEntity.ok(true);
    }


    @RequestMapping("driverInfo")
    public Driver getDriverInfoOfID(String driverId) {
        var driver = handler.id2DriverDBCache.get(driverId);
        if (!(driver == null)) {
            UnitedLog.print("Cache Hit: " + driverId);
        } else {
            UnitedLog.err("Cache Miss: " + driverId);
            driver = driverService.queryById(driverId);
        }
        driver.setPasswordSha256("");
        return driver;
    }

    // 顾客给司机
    @RequestMapping("go-on-reserved-bill")
    public ResponseEntity<String> goOnReservedBill(String billId) {
        var reservedBill = reserveBillService.queryById(billId);
        var customerId = reservedBill.getCustomerId();
        var driverId = reservedBill.getDriverId();
        UnitedLog.print("customerId of reservedBill: " + customerId);
        UnitedLog.print("driverId of reservedBill: " + driverId);
        var bill = billService.queryById(billId);
        var LngLatA = binUtils.parseLL2Double(bill.getFromPlace());
        var LngLatB = binUtils.parseLL2Double(bill.getToPlace());
        exe.execute(() -> {
            var msg =
            """
            {
                "goOnReservedBill": true,
                "preBillId": "%s",
                "customerId": "%s",
                "lng": %f,
                "lat": %f,
                "lng2": %f,
                "lat2": %f
            }
            """.formatted(billId, customerId, LngLatA[0], LngLatA[1], LngLatB[0], LngLatB[1]);
            try {
                handler.send2driver(driverId, msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok("success");
    }


    @RequestMapping("customerInfo")
    public Customer getCustomerInfoOfID(String customerId) {
        var customer = handler.id2customerDBCache.get(customerId);
        if (!(customer == null)) {
            UnitedLog.print("Cache Hit: " + customerId);
        } else {
            UnitedLog.err("Cache Miss: " + customerId);
            customer = customerService.queryById(customerId);
        }

        customer.setPasswordSha256("");
        Customer finalCustomer = customer;
        exe.execute(() -> handler.id2customerDBCache.put(customerId, finalCustomer));

        return customer;
    }

    public String lngLat2String(double lng, double lat) {
        return "[%f, %f]".formatted(lng, lat);
    }

    @RequestMapping("query4new-travel")
    // dateTime format: yyyy-MM-dd HH:mm:ss.SSS
    // 如果 isYoYaKu 是 true, 是顾客发起的预约请求系列中的 第一个.
    public ResponseEntity<String> newTravel(String customerId, double lng, double lat, double lng2, double lat2,
                                            Optional<Boolean> isYoYaKu,
                                            Optional<String> dateTime) {
        var YuYue = isYoYaKu.isPresent() && isYoYaKu.get() && dateTime.isPresent();
        var preBillId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + customerId;
        Bill b_ = new Bill();
        var flag = new AtomicBoolean(false);
        var flag_YYK = new AtomicBoolean(false);
        handledMap.put(preBillId, flag);
        if (YuYue) {
            handleMap_YYK.put(preBillId, flag_YYK);
        }
        b_.setId(preBillId);
        b_.setStatus("HANGING");
        b_.setFromPlace(lngLat2String(lng, lat));
        b_.setCustomerId(customerId);
        b_.setToPlace(lngLat2String(lng2, lat2));
        b_.setTime(Timestamp.from(Instant.now()));
        billMap.put(preBillId, b_);
        exe.execute(() -> billService.insert(b_));
        List<String> availableDrivers;
        if (YuYue) {
            UnitedLog.print("是预约");
            exe.execute(() -> {
                ReserveBill reserveBill = new ReserveBill();
                reserveBill.setBillId(preBillId);
                reserveBill.setCustomerId(customerId);
                reserveBill.setReserveOngoingTime(dateTime.get());
                reserveBillService.insert(reserveBill);
            });
            var drivers = driverService.getAll();
            drivers.removeIf((driver -> !this.handler.id2DriverDBCache.containsKey(driver.getId())));
            availableDrivers = this.mainService.yoYaKu__getChiKaiKuRuMasOfKyaKu(customerId, lng, lat, drivers);
            UnitedLog.print("");
            availableDrivers.forEach((d) -> {
                System.out.print(d.concat("\t"));
            });
        } else {
            availableDrivers = this.mainService.getChiKaiKuRuMasOfKyaKu(customerId, lng, lat);
        }
        exe.execute(() -> {
            try {
                for (var driverId : availableDrivers) {
                    if (!flag.get()) {
                        if (!YuYue) {
                            handler.send2driver4billCatch(preBillId, driverId, customerId, lng, lat, lng2, lat2);
                            sleep(3000);
                        } else {
                            if (!flag_YYK.get()) {
                                var json_ = """
                                {
                                    "preBillId": "%s",
                                    "customerId": "%s",
                                    "lng": %f,
                                    "lat": %f,
                                    "lng2": %f,
                                    "lat2": %f,
                                    "isYoYaKu": true,
                                    "dateTime": "%s"
                                }""".formatted(preBillId, customerId, lng, lat, lng2, lat2, dateTime.get());
                                handler.send2driver(driverId, json_);
                                sleep(3000);
                            }
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        return ResponseEntity.ok("success");
    }


    @RequestMapping("handle-new-travel")
    public synchronized String handleNewTravel(String preBillId, String customerId, String driverId, Optional<Boolean> isProcessingReservedBill) {
        var yyk = isProcessingReservedBill.isPresent() && isProcessingReservedBill.get();

        UnitedLog.print("接单的司机的 ID: " + driverId);
        var flag = handledMap.get(preBillId);
        if (flag.get()) {
            var str = "ERR: preBillId " + preBillId + " had been handled";
            UnitedLog.err(str);
            return str;
        }
        flag.set(true);
        this.mainService.bind(driverId, customerId);
        var bill = billMap.get(preBillId);
        bill.setStatus("GOING");
        bill.setDriverId(driverId);
        exe.execute(() -> {
            billService.update(bill);
            var driverPlace = mainService.getWorkingDriverPlaceOf(driverId).split(",");
            var lng = Double.parseDouble(driverPlace[0]);
            var lat = Double.parseDouble(driverPlace[1]);
            var msg =
                    """
                    {
                         "ID": "%s",
                         "lng": %f,
                         "lat": %f,
                         "yyk": %b
                    }
                    """.formatted(driverId, lng, lat, yyk);
            UnitedLog.print(msg);
            try {
                handler.send2customer(customerId, msg);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return "success";
    }

    @RequestMapping("handle-YYK-bill-by-driver")
    public synchronized String handleYYKBill(String preBillId, String customerId, String driverId) {
        var flag = handleMap_YYK.get(preBillId);
        if (flag.get()) {
            var str = "Reserved Bill : " + preBillId + " had been handled";
            UnitedLog.err(str);
            return str;
        }
        flag.set(true);
        var bill = billMap.get(preBillId);
        bill.setStatus("WAITING");
        bill.setDriverId(driverId);
        exe.execute(() -> {
            billService.update(bill);
            var msg =
                    """
                    {
                        "ID": "%s",
                        "YYK_GOT": true,
                        "reservedBillId": "%s"
                    }
                    """.formatted(driverId, preBillId);
            try {
                handler.send2customer(customerId, msg);
                ReserveBill reserveBill = reserveBillService.queryById(preBillId);
                reserveBill.setDriverId(driverId);
                reserveBillService.update(reserveBill);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return "success";
    }


    // 车主
    @RequestMapping("store1bill")
    public void storeBillByCustomer(String billId, String customerId, String money) {
        Bill bill = billMap.get(billId);
        Timestamp startTime = bill.getTime();
        bill.setDuration(binUtils.calcTimeDuration(startTime.toInstant(), Instant.now()));
        bill.setMoney(money);
        bill.setStatus("NOT_PAID");
        exe.execute(() -> billService.update(bill));
        exe.execute(() -> {
            try {
                handler.send2customer(customerId, "{ \"billId\": \"%s\", \"money\": \"%s\" }".formatted(billId, money));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }


    @RequestMapping("raise-dispute")
    public ResponseEntity<String> raiseDispute(String billId, String type, String contents) {
        var dis = new Dispute();
        dis.setBillId(billId);
        dis.setType(type);
        dis.setStatus("HANGING");
        dis.setTime(Timestamp.from(Instant.now()));
        exe.execute(() -> {
            var bill = billService.queryById(billId);
            dis.setCustomerId(bill.getCustomerId());
            dis.setDriverId(bill.getDriverId());
            dis.setId(Instant.now().toString() + dis.getCustomerId());
            dis.setContents(contents);
            disputeService.insert(dis);
        });
        return ResponseEntity.ok("success");
    }


    @RequestMapping("cancel-dispute")
    public  ResponseEntity<String> cancelDispute(String disputeId) {
        var dis = disputeService.queryById(disputeId);
        if (dis == null) {
            return ResponseEntity.ok("failed: cannot find dispute with id \"%s\"".formatted(disputeId));
        }
        var billId = dis.getBillId();
        var bill = billService.queryById(billId);
        bill.setStatus("FINISHED");
        billService.update(bill);
        boolean b = disputeService.deleteById(disputeId);
        return b ? ResponseEntity.ok("success") : ResponseEntity.ok("failed: cannot delete dispute with id \"%s\"".formatted(disputeId)) ;
    }

    // 顾客
    @RequestMapping("PayAndGiveScore")
    public void giveScore(String billId, Optional<Integer> score_) {
        var bill = billMap.get(billId);
        Integer score;
        UnitedLog.print("是否打了分：" + score_.isPresent());
        score_.ifPresent(integer -> UnitedLog.print("分数：" + integer));
        if (score_.isPresent()) {
            score = score_.get();
            bill.setScore(score);
            bill.setStatus("FINISHED");
        } else {
            bill.setStatus("NOT_SCORED");
        }
        exe.execute(() -> {
            billService.update(bill);
            var driverId = bill.getDriverId();
            if (score_.isPresent()) {
                Driver driver = driverService.queryById(driverId);
                driver.setScore((driver.getScore() * driver.getRunTimes() + score_.get()) / (driver.getRunTimes() + 1));
                driverService.update(driver);
            }
        });
    }

    @RequestMapping("get-all-drivers")
    public List<Driver> getAllDrivers() {
        return null;
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("getFreeDriverPlaces")
    public String getFreeDriverPlaces() {
        return this.mainService.getFreeDriverPlaces();
    }

    @RequestMapping("caught")
    public String together(String customerId) {
        try {
            handler.send2customer(customerId, "{ \"CAUGHT\": true }");
        } catch (IOException e) {
            exe.execute(() -> {
                throw new RuntimeException(e);
            });
            return "failed";
        }
        return "success";
    }


    @RequestMapping("rideEnd")
    public String rideEnd(String driverId, String customerId) {
        exe.execute(() -> {
            Driver driver = driverService.queryById(driverId);
            driver.setRunTimes(driver.getRunTimes() + 1);
            driverService.update(driver);
        });
        try {
            handler.send2customer(customerId, "{ \"CAUGHT\": false, \"FINISHED\": true }");
            mainService.unBind(driverId, customerId);
        } catch (IOException e) {
            exe.execute(() -> {
                throw new RuntimeException(e);
            });
            return "failed";
        }
        return "success";
    }

    @RequestMapping("getFreeCustomerPlaces")
    public String getFreeCustomerPlaces() {
        return this.mainService.getFreeCustomerPlaces();
    }

    @RequestMapping("getWorkingDriverPlaceOf")
    public String getWorkingDriverPlaceOf(String id) {
        return this.mainService.getWorkingDriverPlaceOf(id);
    }

    @RequestMapping("getGoingCustomerPlaceOf")
    public String getGoingCustomerPlaceOf(String id) {
        return this.mainService.getGoingCustomerPlaceOf(id);
    }

    @RequestMapping("updateDriverPlace")
    public void updateDriverPlace(String id, double lng, double lat) {
        this.mainService.updateDriverPlace(id, lng, lat);
    }

    @RequestMapping("getChiKaiKuRuMasOfKyaKu")
    List<String> getChiKaiKuRuMasOfKyaKu(String kyaId, double lng, double lat) {
        return this.mainService.getChiKaiKuRuMasOfKyaKu(kyaId, lng, lat);
    }

    @RequestMapping("updateCustomerPlace")
    public void updateCustomerPlace(String id, double lng, double lat) {
        this.mainService.updateCustomerPlace(id, lng, lat);
    }

    @RequestMapping("makeDriverBusy")
    public void makeDriverBusy(String id) {
        this.mainService.makeDriverBusy(id);
    }

    @RequestMapping("makeCustomerBusy")
    public void makeCustomerBusy(String id) {
        this.mainService.makeCustomerBusy(id);
    }

    @RequestMapping("makeDriverFree")
    public void makeDriverFree(String id) {
        this.mainService.makeDriverFree(id);
    }

    @RequestMapping("makeCustomerFree")
    public void makeCustomerFree(String id) {
        this.mainService.makeCustomerFree(id);
    }

    @RequestMapping("bind")
    public void bind(String driverId, String customerId) {
        this.mainService.bind(driverId, customerId);
    }

    @RequestMapping("unBind")
    public void unBind(String driverId, String customerId) {
        this.mainService.unBind(driverId, customerId);
    }

    @Autowired
    @Qualifier("exe")
    public void setExe(Executor exe) {
        this.exe = exe;
    }

    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    @Autowired
    public void setBinUtils(BinUtils binUtils) {
        this.binUtils = binUtils;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Autowired
    public void setReserveBillService(ReserveBillService reserveBillService) {
        this.reserveBillService = reserveBillService;
    }

    @Autowired
    public void setDisputeService(DisputeService disputeService) {
        this.disputeService = disputeService;
    }
}
