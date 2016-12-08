package com.report.controller;

import com.report.service.ReportClientService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class ReportClientController {

    @Autowired
    ReportClientService reportClientService;


    private final Map<String, List<String>> userDb = new HashMap<>();



    public ReportClientController() {
        userDb.put("tom", Arrays.asList("user"));
        userDb.put("sally", Arrays.asList("user", "admin"));
    }


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity<String> login(HttpServletResponse response) throws ServletException {

        String userToken = reportClientService.login();

        if (StringUtils.isBlank(userToken))
          return   new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);

        response.addCookie(new Cookie("Authorization", userToken));
        return new ResponseEntity<String>(HttpStatus.OK);
    }


}
