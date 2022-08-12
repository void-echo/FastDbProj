package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (Car)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
public class Car implements Serializable {
    private static final long serialVersionUID = 151310771544418924L;

    private String id;

    private String startUsingTime;

    private String band;

    private Integer maxSpeed;

    private String carType;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartUsingTime() {
        return startUsingTime;
    }

    public void setStartUsingTime(String startUsingTime) {
        this.startUsingTime = startUsingTime;
    }

    public String getBand() {
        return band;
    }

    public void setBand(String band) {
        this.band = band;
    }

    public Integer getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Integer maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

}

