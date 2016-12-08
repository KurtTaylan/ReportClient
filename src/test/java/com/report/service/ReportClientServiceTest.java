package com.report.service;

import com.report.AbstractReportClientTest;
import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Componant Test Cases for importing file.
 *  <p>
 *  Test cases:
 *  <p>
 *  TC1- login successful
 *  TC2- login fail
 *  <p>
 */
public class ReportClientServiceTest extends AbstractReportClientTest {



    @Test
    public void testLoginSuccess(){
        User backdoorUser = new User("","");
        Optional<String> validToken = reportClientService.login(backdoorUser);            // Backdoor: If you leave it blank than service uses valid credentials for testing purposes.

        assertThat(validToken.isPresent()).isTrue();
    }


    @Test()
    public void testLoginFail(){
        User fakeUser = new User("fakesample@gmail.com","fail it");
        Optional<String> validToken = reportClientService.login(fakeUser);

        assertThat(validToken.isPresent()).isFalse();
    }


    @Test
    public void testReport(){
        ReportCriterias criterias = new ReportCriterias();
        Optional<Report> report = reportClientService.makeReport(criterias);


    }



}
