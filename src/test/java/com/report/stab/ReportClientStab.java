package com.report.stab;


import com.report.dto.report.ReportCriterias;

/**
 * Stab class for ReportClient scenarios
 */
public class ReportClientStab {



    public static ReportCriterias createSampleReportCriterias(){
        ReportCriterias criterias = new ReportCriterias();

        criterias.setFromDate("2015-07-01");
        criterias.setToDate("2015-10-01");
        criterias.setMerchant(1);
        criterias.setAcquirer(1);

        return criterias;
    }


    public static void create5RecordUniqueList(){

    }



    public static void create5DublicatesList(){

    }


    public static void createListFor3Dub5Total(){

    }
}
