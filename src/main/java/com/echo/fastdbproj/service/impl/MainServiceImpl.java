package com.echo.fastdbproj.service.impl;

import com.echo.fastdbproj.service.MainService;
import com.echo.fastdbproj.util.BinUtils;
import com.echo.fastdbproj.util.JsonProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MainServiceImpl implements MainService {
    Map<String, List<Double>> driverPlaceMap, customerPlaceMap;
    Set<String> busyDriverIds = new HashSet<>(), busyCustomerIds = new HashSet<>();
    Map<String, String> binder = new HashMap<>();
    BinUtils utils;
    JsonProvider json;


    public static void main(String[] args) {

    }

    @Override
    public String getFreeDriverPlaces() {
        var mapper = new HashMap<>(driverPlaceMap);
        busyDriverIds.forEach(mapper::remove);
        var str = json.toJson(mapper);
        System.out.println("str: \n" + str);
        return str;
    }

    @Override
    public String getFreeCustomerPlaces() {
        var mapper = new HashMap<>(customerPlaceMap);
        busyCustomerIds.forEach(mapper::remove);
        var str = json.toJson(mapper);
        System.out.println("str: \n" + str);
        return str;
    }

    @Override
    public String getWorkingDriverPlaceOf(String id) {
        return json.toJson(driverPlaceMap.get(id));
    }

    @Override
    public String getGoingCustomerPlaceOf(String id) {
        return json.toJson(customerPlaceMap.get(id));
    }

    @Override
    public List<String> getChiKaiKuRuMasOfKyaKu(String kyaId, double lng, double lat) {
        List<String> driverIds = new ArrayList<>(driverPlaceMap.keySet());
        driverIds.removeIf(busyDriverIds::contains);
        driverIds.sort((a, b) ->
                Double.compare(utils.calcDistance(driverPlaceMap.get(a).get(0), driverPlaceMap.get(a).get(1), lng, lat),
                 (utils.calcDistance(driverPlaceMap.get(b).get(0), driverPlaceMap.get(b).get(1), lng, lat)))
        );
        return driverIds;
    }

    @Override
    public void updateDriverPlace(String id, double lng, double lat) {
        driverPlaceMap.put(id, List.of(lng, lat));
    }

    @Override
    public void updateCustomerPlace(String id, double lng, double lat) {
        customerPlaceMap.put(id, List.of(lng, lat));
    }

    @Override
    public void makeDriverBusy(String id) {
        this.busyDriverIds.add(id);
    }

    @Override
    public void makeCustomerBusy(String id) {
        this.busyCustomerIds.add(id);
    }

    @Override
    public void makeDriverFree(String id) {
        this.busyDriverIds.remove(id);
    }

    @Override
    public void makeCustomerFree(String id) {
        this.busyCustomerIds.remove(id);
    }

    @Override
    public void bind(String driverId, String customerId) {
        this.binder.put(driverId, customerId);
    }

    @Override
    public void unBind(String driverId, String customerId) {
        this.binder.remove(driverId, customerId);
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

    @Autowired
    public void setJson(JsonProvider provider) {
        this.json = provider;
    }

    @Autowired
    public void setUtils(BinUtils utils) {
        this.utils = utils;
    }
}
