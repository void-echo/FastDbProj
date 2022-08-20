package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.MyWebSocketHandler;
import com.echo.fastdbproj.entity.Customer;
import com.echo.fastdbproj.entity.Driver;
import com.echo.fastdbproj.service.CustomerService;
import com.echo.fastdbproj.service.DriverService;
import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.util.UnitedLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

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

    AtomicBoolean isNewTravelHandled = new AtomicBoolean(false);
    Map<String, AtomicBoolean> handledMap = new HashMap<>(); // map preBillId 2 isCaught
    Executor exe;

    @Autowired
    public void setHandler(MyWebSocketHandler handler) {
        this.handler = handler;
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
        return customer;
    }

    @RequestMapping("query4new-travel")
    public void newTravel(String customerId, double lng, double lat) {
        var preBillId = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + customerId;
        var flag = new AtomicBoolean(false);
        handledMap.put(preBillId, flag);
        var availableDrivers = this.mainService.getChiKaiKuRuMasOfKyaKu(customerId, lng, lat);
        exe.execute(() -> {
            try {
                for (var driverId : availableDrivers) {
                    if (!flag.get()) {
                        handler.send2driver4billCatch(preBillId, driverId, customerId, lng, lat);
                        wait(3000);
                    }
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    @RequestMapping("handle-new-travel")
    public synchronized String handleNewTravel(String preBillId, String customerId, String driverId) {
        var flag = handledMap.get(preBillId);
        if (flag.get()) {
            var str = "ERR: preBillId " + preBillId + " had been handled";
            UnitedLog.err(str);
            return str;
        }
        flag.set(true);
        this.mainService.bind(driverId, customerId);
        exe.execute(() -> {
            try {
                handler.send2customer(customerId, "ID: " + driverId);
                handler.send2customer(customerId, "PLACE: " + mainService.getWorkingDriverPlaceOf(driverId));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return "success";
    }

    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @RequestMapping("getFreeDriverPlaces")
    public String getFreeDriverPlaces() {
        return this.mainService.getFreeDriverPlaces();
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
}
