package com.report.dto;

/**
 * Created by taylan on 08.12.2016.
 */
public enum CacheEnum {

    CURRENTUSERMAIL("loggedUserEmail");

    private String cache;

    CacheEnum(String cache) {
        this.cache = cache;
    }

    public String getCache() {
        return cache;
    }

}
