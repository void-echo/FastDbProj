package com.echo.fastdbproj.entity;

import java.io.Serializable;

/**
 * (Driver)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:12
 */
public class Driver implements Serializable {
    private static final long serialVersionUID = -73934158841942834L;

    private String id;

    private String name;

    private String tel;

    private String mail;

    private Integer runTimes;

    private String passwordSha256;

    private String preferPlace;

    private Double score;

    private String carId;

    public Driver() {
        super();
        this.runTimes = 0;
        this.preferPlace = "";
        this.carId = null;
        this.score = 5.0;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getRunTimes() {
        return runTimes;
    }

    public void setRunTimes(Integer runTimes) {
        this.runTimes = runTimes;
    }

    public String getPasswordSha256() {
        return passwordSha256;
    }

    public void setPasswordSha256(String passwordSha256) {
        this.passwordSha256 = passwordSha256;
    }

    public String getPreferPlace() {
        return preferPlace;
    }

    public void setPreferPlace(String preferPlace) {
        this.preferPlace = preferPlace;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

}

