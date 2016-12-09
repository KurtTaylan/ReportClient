package com.report.dto.transaction;

public class Fx {


    private Merchant merchant;

    public Fx() {
    }

    public Fx(Merchant merchant) {
        this.merchant = merchant;
    }

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public static class Merchant{


        private int originalAmount;
        private String originalCurrency;

        public Merchant() {
        }

        public Merchant(int originalAmount, String originalCurrency) {
            this.originalAmount = originalAmount;
            this.originalCurrency = originalCurrency;
        }

        public int getOriginalAmount() {
            return originalAmount;
        }

        public void setOriginalAmount(int originalAmount) {
            this.originalAmount = originalAmount;
        }

        public String getOriginalCurrency() {
            return originalCurrency;
        }

        public void setOriginalCurrency(String originalCurrency) {
            this.originalCurrency = originalCurrency;
        }
    }
}
