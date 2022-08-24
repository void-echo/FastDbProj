package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (DriverCard)实体类
 *
 * @author makejava
 * @since 2022-08-24 22:56:06
 */
public class DriverCard implements Serializable {
    private static final long serialVersionUID = 554719782112734187L;

    private String driverId;

    private String cardId;


    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

}

