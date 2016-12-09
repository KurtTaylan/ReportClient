package com.report.service.impl;

import com.report.client.ReportClient;
import com.report.dto.APIURLs;
import com.report.dto.login.LoginResponse;
import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.dto.transaction.TransactionResult;
import com.report.service.ReportClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Optional;

@Service("reportClientService")
public class ReportClientServiceImpl implements ReportClientService, Serializable {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ReportClient client;

    @Autowired
    private HttpSession httpSession;

    @Value("${report.service.url}")
    private String hostUrl;

    @Value("${report.test.user.email}")
    private String sampleUserEmail;

    @Value("${report.test.user.password}")
    private String sampleUserPassword;



    @Override
    public Optional<String> login(User loginUser) {
        String loginUrl = hostUrl + APIURLs.LOGIN.getUrl();

        MultiValueMap<String, String> requestMap = setUserCredentials(loginUser.getEmail(), loginUser.getPassword());
        Optional<LoginResponse> loginResponse = client.loginWithCredentials(loginUrl, requestMap);

        if (loginResponse.isPresent() && "APPROVED".equals(loginResponse.get().getStatus())) {
            httpSession.setAttribute("Authorization",loginResponse.get().getToken());

            return Optional.ofNullable(loginResponse.get().getToken());
        } else
            return Optional.empty();
    }

    private MultiValueMap<String, String> setUserCredentials(String email, String password) {
        if ("".equals(email) && "".equals(password)) {
            email = sampleUserEmail;
            password = sampleUserPassword;
        }

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("email", email);
        requestMap.add("password", password);

        return requestMap;
    }


    @Override
    public Optional<Report> makeReport(ReportCriterias reportCriterias) {
        String reportUrl = hostUrl + APIURLs.REPORT.getUrl();

        Optional<Report> report = null;
        if (isMeaningful(reportCriterias)) {

            String authorization = (String) httpSession.getAttribute("Authorization");
            if (authorization != null) {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.set("Authorization", authorization);

                report = client.makeReportWith(reportUrl, reportCriterias, headers);
            }else
                throw new IllegalArgumentException("There is no valid token!");

        } else {
            throw new IllegalArgumentException("Report criterias are not suitable to make report.");
        }

        return report;
    }

    private boolean isMeaningful(ReportCriterias reportCriterias) {
        boolean result = false;
        String fromDate = reportCriterias.getFromDate();
        String toDate = reportCriterias.getToDate();

        if (fromDate != null && toDate != null && LocalDate.parse(fromDate).isBefore(LocalDate.parse(toDate)))
            result = true;

        return result;
    }


    @Override
    public Optional<TransactionResult> fetchTransaction(String transactionId) {
        String transactionUrl = hostUrl + APIURLs.TRANSACTION.getUrl();
        Optional<TransactionResult> transactionResult = client.fetchTransactionFrom(transactionUrl, transactionId);

        return transactionResult;
    }


    @Override
    public Optional<String> listTransactions(String transactionId) {
        String transactionUrl = hostUrl + APIURLs.TRANSACTIONLIST.getUrl();
        Optional<String> result = client.listTransactionsFrom(transactionUrl, transactionId);

        return result;
    }
}
