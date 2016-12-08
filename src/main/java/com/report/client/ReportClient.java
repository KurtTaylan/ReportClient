package com.report.client;

import com.report.dto.login.LoginResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 *  Client template for requesting Clearsettle Reporting service.
 */
@Component
public class ReportClient {


    private Logger logger = LoggerFactory.getLogger(this.getClass());



    public Optional<LoginResponse> loginWithCredentials(String loginUrl, MultiValueMap<String, String> requestMap) {
        RestTemplate restTemplate = new RestTemplate();
        LoginResponse loginResponse = null;

        try{
            loginResponse = restTemplate.postForObject(loginUrl, requestMap, LoginResponse.class);
        }catch (Exception e){
            logger.error("Exception occured while waiting server respond",e);
        }

        return Optional.ofNullable(loginResponse);
    }
}
