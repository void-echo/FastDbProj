package com.echo.fastdbproj;

import com.echo.fastdbproj.entity.Customer;
import com.echo.fastdbproj.entity.Driver;
import com.echo.fastdbproj.service.CustomerService;
import com.echo.fastdbproj.service.DriverService;
import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.util.BinUtils;
import com.echo.fastdbproj.util.UnitedLog;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.Executor;


@Component
public class MyWebSocketHandler implements WebSocketHandler {
    MainService mainService;
    ConcurrentHashMap<String, WebSocketSession> id2session;
    ConcurrentHashMap<String, String> sessionId2Id;
    CopyOnWriteArraySet<String> sessionIds;

    public Map<String, Driver> id2DriverDBCache;
    public Map<String, Customer> id2customerDBCache;
    public Map<String, String> id2CarJsonIncludeBase64;

    CustomerService customerService;
    DriverService driverService;

    Executor exe;


    BinUtils binUtil;



    public MyWebSocketHandler() {
        id2session = new ConcurrentHashMap<>();
        sessionIds = new CopyOnWriteArraySet<>();
        sessionId2Id = new ConcurrentHashMap<>();
        id2DriverDBCache = new HashMap<>();
        id2customerDBCache = new HashMap<>();
        id2CarJsonIncludeBase64 = new HashMap<>();
    }

    public void send2driver4billCatch(String preBillId, String send2driverId, String customerId, double lng, double lat, double lng2, double lat2) throws IOException {
        UnitedLog.print(send2driverId);
        id2session.get(send2driverId).sendMessage(new TextMessage(
                "{ \"preBillId\": \"" + preBillId
                        + "\" , \"customerId\": \"" + customerId
                        + "\" , \"lng\": " + lng
                        + " , \"lat\": " + lat
                        + " , \"lng2\": " + lng2
                        + " , \"lat2\": " + lat2
                        + "}"
        ));
    }

    public void send2driver(String driverId, String msg)  throws IOException {
        id2session.get(driverId).sendMessage(new TextMessage(msg));
    }

    public void send2customer(String customerId, String msg) throws IOException {
        id2session.get(customerId).sendMessage(new TextMessage(msg));
    }


    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) {
        UnitedLog.print("链接 ID: " + session.getId() + ";  URI: " + session.getUri() + " 建立成功");
    }

    @Override
    public void handleMessage(@NotNull WebSocketSession session, @NotNull WebSocketMessage<?> message) throws IOException {
        var msg = message.getPayload();
        UnitedLog.print(msg.toString());
        var sessionId = session.getId();
        if (msg instanceof String str) {
            if (!sessionIds.contains(sessionId)) {
                sessionIds.add(sessionId);
                var realId = binUtil.parseId(str);
                if (!sessionId2Id.isEmpty()) {
                    sessionId2Id.values().remove(realId);
                }
                id2session.put(realId, session);
                sessionId2Id.put(sessionId, realId);

                exe.execute(() -> {
                    Customer customer = customerService.queryById(realId);
                    Driver driver = driverService.queryById(realId);
                    if (customer != null) {
                        id2customerDBCache.put(realId, customer);
                    } else if (driver != null) {
                        id2DriverDBCache.put(realId, driver);
                    } else {
                        UnitedLog.err("ID: " + realId + " is neither a Customer nor a Driver");
                    }
                });

            } else {
                // 如果客户端通过 socket 向服务器发送超过了一次数据, 就认为是司机发的.
                double[] lngLat = binUtil.parseLngLat2Double(str);
                var driverId = sessionId2Id.get(sessionId);
                mainService.dualUpdate(driverId, lngLat[0], lngLat[1]);
                var customerId = mainService.getDualCustomerIdOfWorkingDriver(driverId);
                if (customerId != null) {
                    id2session.get(customerId).sendMessage(new TextMessage("{ \"lng\": " + lngLat[0] + ", \"lat\": " + lngLat[1] + " }"));
                } else {
                    UnitedLog.print("司机目前没有接到乘客, 不进行坐标转发. ");
                }
            }
        } else UnitedLog.err("Parsing ERR: Payload: " + msg + " is not a String.");
    }

    @Override
    public void handleTransportError(@NotNull WebSocketSession session, @NotNull Throwable exception) {
        exception.printStackTrace();
    }

    @Override
    public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus closeStatus) {
        var sessionId = session.getId();
        var realId = sessionId2Id.get(sessionId);
        id2session.remove(realId);
        sessionId2Id.remove(sessionId);
        sessionIds.remove(sessionId);
        UnitedLog.print("链接 ID: " + session.getId() + ";  URI: " + session.getUri() + " 链接关闭");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }


    @Autowired
    public void setMainService(MainService mainService) {
        this.mainService = mainService;
    }

    @Autowired
    public void setBinUtil(BinUtils binUtil) {
        this.binUtil = binUtil;
    }

    @Autowired
    public void setExe(Executor exe) {
        this.exe = exe;
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }


    @Autowired
    public void setDriverService(DriverService driverService) {
        this.driverService = driverService;
    }
}
