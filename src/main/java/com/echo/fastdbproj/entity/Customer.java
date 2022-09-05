package com.echo.fastdbproj.entity;

import java.io.Serial;
import java.io.Serializable;

/**
 * (Customer)实体类
 *
 * @author makejava
 * @since 2022-08-11 14:08:10
 */
public class Customer implements Serializable {
    @Serial
    private static final long serialVersionUID = -88995768963099356L;

    private String id;

    private String name;

    private String tel;

    private String mail;

    private String passwordSha256;


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

    public String getPasswordSha256() {
        return passwordSha256;
    }

    public void setPasswordSha256(String passwordSha256) {
        this.passwordSha256 = passwordSha256;
    }

}

