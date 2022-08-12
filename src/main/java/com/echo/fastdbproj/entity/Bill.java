package com.echo.fastdbproj.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Bill)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:09
 */
public class Bill implements Serializable {
    private static final long serialVersionUID = 654896225109029445L;

    private String id;

    private Date time;

    private String money;

    private Integer score;

    private String driverId;

    private String customerId;

    private String status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
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
        this.status = status;
    }

}

