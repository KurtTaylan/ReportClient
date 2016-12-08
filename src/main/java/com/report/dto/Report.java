package com.report.dto;

public class Report {


    private int numOfProcessedPersons;



    private Report(Builder builder) {
        this.numOfProcessedPersons = builder.numOfProcessedPersons;
    }



    public int getNumOfProcessedPersons() {
        return numOfProcessedPersons;
    }


    @Override
    public String toString() {
        if (this.numOfProcessedPersons != 0)
            return String.format("Number of processed unique person is: %s", this.numOfProcessedPersons);
        else
            return "There is no person found to be processed";
    }



    public static class Builder {

        private int numOfProcessedPersons;


        public Builder persons(int numOfProcessedPersons) {
            this.numOfProcessedPersons = numOfProcessedPersons;
            return this;
        }


        public Report build() {
            return new Report(this);
        }

    }
}
