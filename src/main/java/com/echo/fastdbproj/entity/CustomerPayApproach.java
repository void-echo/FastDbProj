package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (CustomerPayApproach)实体类
 *
 * @author makejava
 * @since 2022-08-24 22:52:35
 */
public class CustomerPayApproach implements Serializable {
    private static final long serialVersionUID = 352381484575055223L;

    private String customerId;

    private String cardId;

    private String payApproachId;


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getPayApproachId() {
        return payApproachId;
    }

    public void setPayApproachId(String payApproachId) {
        this.payApproachId = payApproachId;
    }

}

