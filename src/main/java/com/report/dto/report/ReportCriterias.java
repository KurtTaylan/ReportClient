package com.report.dto.report;

public class ReportCriterias {


    private String fromDate;                                                                                          // I did declare dates with 'util.Date' instead of 'LocalDate' because of db consistency
    private String toDate;
    private int merchant;
    private int acquirer;



    public ReportCriterias() {
    }

    public ReportCriterias(String fromDate, String toDate, int merchant, int acquirer) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.merchant = merchant;
        this.acquirer = acquirer;
    }



    public int getMerchant() {
        return merchant;
    }

    public void setMerchant(int merchant) {
        this.merchant = merchant;
    }

    public int getAcquirer() {
        return acquirer;
    }

    public void setAcquirer(int acquirer) {
        this.acquirer = acquirer;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
