package com.echo.fastdbproj.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Dispute)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public class Dispute implements Serializable {
    private static final long serialVersionUID = 176092209243473113L;

    private String id;

    private Date time;

    private String driverId;

    private String customerId;

    private String billId;

    private String type;

    private String status;

    private String contents;


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

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

}

