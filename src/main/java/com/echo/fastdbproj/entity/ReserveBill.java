package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (ReserveBill)实体类
 *
 * @author makejava
 * @since 2022-08-27 21:46:11
 */
public class ReserveBill implements Serializable {
    private static final long serialVersionUID = -97584061955122657L;

    private String billId;

    private String customerId;

    private String driverId;

    private String reserveOngoingTime;


    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getReserveOngoingTime() {
        return reserveOngoingTime;
    }

    public void setReserveOngoingTime(String reserveOngoingTime) {
        this.reserveOngoingTime = reserveOngoingTime;
    }

}

