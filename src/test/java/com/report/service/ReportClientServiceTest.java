package com.report.service;

import com.report.AbstractReportClientTest;
import org.junit.Test;

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
        String login = reportClientService.login();

        assertThat(login).isNotNull();
    }

    @Test
    public void testLoginFail(){
        String login = reportClientService.login();

        assertThat(login).isNull();
    }



}
