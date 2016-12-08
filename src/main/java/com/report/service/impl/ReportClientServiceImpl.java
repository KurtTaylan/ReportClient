package com.report.service.impl;

import com.report.dto.APIURLs;
import com.report.dto.LoginResponse;
import com.report.service.ReportClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.Serializable;

@Service("reportClientService")
public class ReportClientServiceImpl implements ReportClientService, Serializable {



    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Value("${report.service.url}")
    private String hostUrl;

    @Value("${report.test.user.email}")
    private String userEmail;

    @Value("${report.test.user.password}")
    private String userPassword;



    @Override
    public String login() {
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = setUserCredentials();

        LoginResponse loginResponse = restTemplate.postForObject(hostUrl + APIURLs.LOGIN.getUrl(),
                                                                  requestMap,
                                                                  LoginResponse.class);

        if ("APPROVED".equals(loginResponse.getToken()))
            return loginResponse.getToken();
        else
            return "";
    }

    private MultiValueMap<String, String> setUserCredentials() {
        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        requestMap.add("email", userEmail);
        requestMap.add("password", userPassword);
        return requestMap;
    }
}
