package com.echo.fastdbproj.entity;

import com.echo.fastdbproj.util.UnitedLog;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;
import java.util.Set;

/**
 * (Dispute)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:11
 */
public class Dispute implements Serializable {
    @Serial
    private static final long serialVersionUID = 176092209243473113L;
    public static final Set<String> dispute_possible_status = Set.of(
            "HANGING", "FINISHED", "CANCELED"
    );

    private String id;

    private Date time;

    private String driverId;

    private String customerId;

    private String billId;

    private String type;

    private String status;

    private String contents;

    private String judgeResult;

    public String getJudgeResult() {
        return judgeResult;
    }

    public void setJudgeResult(String judgeResult) {
        this.judgeResult = judgeResult;
    }

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
        // 金额问题
        // 安全问题
        // 其他问题
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (dispute_possible_status.contains(status))
            this.status = status;
        else
            UnitedLog.err("Unsupported Dispute Status: " + status + "\nExpected: " + dispute_possible_status);
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Dispute{" +
                "id='" + id + '\'' +
                ", time=" + time +
                ", driverId='" + driverId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", billId='" + billId + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", contents='" + contents + '\'' +
                ", judgeResult='" + judgeResult + '\'' +
                '}';
    }
}

