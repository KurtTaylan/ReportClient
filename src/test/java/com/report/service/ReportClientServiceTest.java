package com.report.service;

import com.report.AbstractReportClientTest;
import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.dto.transaction.TransactionResult;
import com.report.stab.ReportClientStab;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *  Componant Test Cases for importing file.
 *  <p>
 *  Test cases:
 *  <p>
 *  TC1- Login successful - Happy path
 *  TC2- Login fail
 *  TC3- Make report  - Happy path
 *  TC4- FetchTransaction - Happy Path
 *  TC5- List transactions - Happy path
 *  <p>
 */
public class ReportClientServiceTest extends AbstractReportClientTest {

    @Autowired
    HttpSession session;


    @Test
    public void testLoginSuccess(){
        User backdoorUser = new User("","");
        Optional<String> validToken = reportClientService.login(backdoorUser);                                           // Backdoor: If you leave it blank than service uses valid credentials for testing purposes.

        assertThat(validToken.isPresent()).isTrue();
    }


    @Test()
    public void testLoginFail(){
        User fakeUser = new User("fakesample@gmail.com","fail it");
        Optional<String> validToken = reportClientService.login(fakeUser);

        assertThat(validToken.isPresent()).isFalse();
    }


    @Test
    public void testMakeReport(){
        testLoginSuccess();
        ReportCriterias criterias = ReportClientStab.createSampleReportCriterias();
        Optional<Report> report = reportClientService.makeReport(criterias);

        assertThat(report).isNotNull();
        assertThat(report.get().getStatus()).isEqualTo("APPROVED");
    }


    @Test
    public void testFetchTransaction() throws IOException {
        testLoginSuccess();
        String transactionId = "1-1444392550-1";
        Optional<TransactionResult> transactionService = reportClientService.fetchTransaction(transactionId);

        assertThat(transactionService.get().getFx().getMerchant().getOriginalAmount()).isNotNull();
    }


    @Test
    public void testListTransaction() throws IOException {
        testLoginSuccess();
        String transactionId = "529-1438673740-2";//1-1444392550-1";

        Optional<String> result = reportClientService.listTransactions(transactionId);

        assertThat(result).isNotNull();
    }

}
