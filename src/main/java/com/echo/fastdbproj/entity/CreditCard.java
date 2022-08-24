package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (CreditCard)实体类
 *
 * @author makejava
 * @since 2022-08-24 22:37:49
 */
public class CreditCard implements Serializable {
    private static final long serialVersionUID = 807007974928071402L;

    private String id;

    private String cardType;

    private String organization;

    private Double balance;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}

