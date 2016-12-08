package com.report.dto.login;

/**
 * Created by taylan on 07.12.2016.
 */
public class LoginResponse {



    private String token;

    private String status;

    public LoginResponse() {
    }

    public LoginResponse(String token) {
        this.token = token;
    }

    public LoginResponse(String token, String status) {
        this.token = token;
        this.status = status;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
