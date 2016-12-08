package com.report.dto;

/**
 * Service URLs
 */
public enum APIURLs {

    LOGIN("/api/v3/merchant/user/login"),
    REPORT("/api/v3/transactions/report"),
    TRANSACTIONLIST("/api/v3/transactions/list"),
    TRANSACTION("/api/v3/transaction"),
    CLIENT("/api/v3/client"),
    MERCHANT("/api/v3/merchant");

    private String url;

    APIURLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
