package com.report.dto;

import java.io.Serializable;

/**
 * Created by taylan on 07.12.2016.
 */
public class UserLogin implements Serializable {


    private String name;
    private String password;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
