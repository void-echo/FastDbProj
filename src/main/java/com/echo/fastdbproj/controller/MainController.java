package com.echo.fastdbproj.controller;

import com.echo.fastdbproj.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("running")
public class MainController {
    MainService mainService;

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

}
