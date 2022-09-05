package com.echo.fastdbproj.entity;

import com.echo.fastdbproj.util.UnitedLog;

import java.io.Serial;
import java.io.Serializable;
import java.util.Set;

/**
 * (Bill)实体类
 *
 * @author makejava
 * @since 2022-08-24 15:54:16
 */
public class Bill implements Serializable {
    @Serial
    private static final long serialVersionUID = -16195025527705346L;
    public static final Set<String> POSSIBLE_STATUSES = Set.of(
            "HANGING", "WAITING", "GOING", "NOT_PAID", "NOT_SCORED", "FINISHED", "ON_DISPUTE", "ARCHIVED"
    );

    private String id;
    private java.sql.Timestamp time;

    private String money;

    private Integer score;

    private String driverId;

    private String customerId;

    private String status;

    private String duration;

    private String fromPlace;

    private String toPlace;

    public String getFromPlace() {
        return fromPlace;
    }

    public void setFromPlace(String fromPlace) {
        this.fromPlace = fromPlace;
    }

    public String getToPlace() {
        return toPlace;
    }

    public void setToPlace(String toPlace) {
        this.toPlace = toPlace;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public java.sql.Timestamp getTime() {
        return time;
    }

    public void setTime(java.sql.Timestamp time) {
        this.time = time;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!POSSIBLE_STATUSES.contains(status)) {
            UnitedLog.print("Bill::Status should be in { " + POSSIBLE_STATUSES + " }, \nGot " + status);
        }
        this.status = status;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}

