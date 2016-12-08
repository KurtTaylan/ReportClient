package com.report.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.report.dto.login.LoginResponse;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.util.JsonConvertUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
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


    public Optional<Report> makeReport(String reportUrl, ReportCriterias criterias, HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate();
        String requestBody = getReportRequestBody(criterias);

        HttpEntity<String> entity = new HttpEntity<>(requestBody,headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(reportUrl, HttpMethod.POST,entity,String.class);

        Report report = convertoObjectFromJSON(responseEntity);

        return Optional.ofNullable(report);
    }

    private String getReportRequestBody(ReportCriterias criterias) {
        String requestBody = null;
        try {
            requestBody = JsonConvertUtil.toJsonFrom(criterias);
        } catch (JsonProcessingException e) {
            logger.error("Json convert exception: ",e);
        }
        return requestBody;
    }

    private Report convertoObjectFromJSON(ResponseEntity<String> sadsda) {
        Report report = null;
        try {
            report = JsonConvertUtil.toObjectFromJson(sadsda.getBody(), Report.class);
        } catch (IOException e) {
            logger.error("Json convert exception: ",e);
        }
        return report;
    }
}
