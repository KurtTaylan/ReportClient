package com.report.dto.report;

public class Report {

    private String status;
    private Response response;

    public Report() {
    }

    public Report(String status, Response response) {
        this.status = status;
        this.response = response;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
