package com.echo.fastdbproj.service;

public interface MainService {
    String getFreeDriverPlaces();   // return map json

    String getFreeCustomerPlaces();  // return map json

    String getWorkingDriverPlaceOf(String id);  // return map json

    String getGoingCustomerPlaceOf(String id);  // return map json

    void updateDriverPlace(String id, double lng, double lat);

    void updateCustomerPlace(String id, double lng, double lat);

    void makeDriverBusy(String id);

    void makeCustomerBusy(String id);

    void makeDriverFree(String id);

    void makeCustomerFree(String id);

    void bind(String driverId, String customerId);

    void unBind(String driverId, String customerId);
}
