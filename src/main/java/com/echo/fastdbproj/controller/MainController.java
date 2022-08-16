package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.util.JsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("running")
public class MainController {
    Map<String, List<Double>> driverPlaceMap;
    Map<String, List<Double>> customerPlaceMap;


    JsonProvider json;

    @RequestMapping("update-driver")
    public void updateDriver(String id, double lng, double lat) {
        driverPlaceMap.put(id, List.of(lng, lat));
    }

    @RequestMapping("update-customer")
    public void updateCustomer(String id, double lng, double lat) {
        customerPlaceMap.put(id, List.of(lng, lat));
    }

    @RequestMapping("all-customer-places")
    public String getAllCustomers() {
        return json.toJson(customerPlaceMap);
    }

    @RequestMapping("all-driver-places")
    public String getAllDrivers() {
        return json.toJson(driverPlaceMap);
    }

    @Autowired
    @Qualifier("mapD")
    public void setDriverPlaceMap(Map<String, List<Double>> driverPlaceMap) {
        this.driverPlaceMap = driverPlaceMap;
    }

    @Autowired
    @Qualifier("mapC")
    public void setCustomerPlaceMap(Map<String, List<Double>> customerPlaceMap) {
        this.customerPlaceMap = customerPlaceMap;
    }
}
