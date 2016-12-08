package com.report.service.impl;

import com.report.client.ReportClient;
import com.report.dto.APIURLs;
import com.report.dto.login.LoginResponse;
import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.service.ReportClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;
import java.util.Optional;

@Service("reportClientService")
public class ReportClientServiceImpl implements ReportClientService, Serializable {


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    ReportClient client;

    @Value("${report.service.url}")
    private String hostUrl;

    @Value("${report.test.user.email}")
    private String sampleUserEmail;

    @Value("${report.test.user.password}")
    private String sampleUserPassword;



    @Override
    public Optional<String> login(User loginUser) {
        String loginUrl = hostUrl + APIURLs.LOGIN.getUrl();

        MultiValueMap<String, String> requestMap = setUserCredentials(loginUser.getEmail(),loginUser.getPassword());
        Optional<LoginResponse> loginResponse = client.loginWithCredentials(loginUrl,requestMap);

        if (loginResponse.isPresent() && "APPROVED".equals(loginResponse.get().getStatus()))
            return Optional.ofNullable(loginResponse.get().getToken());
         else
            return Optional.empty();
    }

    private MultiValueMap<String, String> setUserCredentials(String email, String password) {
        if(email == "" && password == "") {
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

//        Optional<Report> report = client.makeReport(reportUrl,reportCriterias);


        return null;
    }
}
