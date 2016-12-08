package com.report.controller;

import com.report.dto.CacheEnum;
import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.service.ReportClientService;
import com.report.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ReportClientController {



    @Autowired
    HttpSession httpSession;

    @Autowired
    ReportClientService reportClientService;



    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam(value = "email", defaultValue ="") String email,
                                        @RequestParam(value = "password", defaultValue ="") String password, HttpServletResponse response) throws ServletException {

        User loginUser = new User(email,password);
        Optional<String> userToken = reportClientService.login(loginUser);

        if (userToken.isPresent()) {
            String token = userToken.get();

            response.addCookie(new Cookie("Authorization", token));
            httpSession.setAttribute("Authorization",token);
            putCredentialOnCache(loginUser, token);

            return new ResponseEntity<String>(HttpStatus.OK);
        }else

            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }

    private void putCredentialOnCache(User loginUser, String token) {
        CacheUtil.cachedAuthToken.put(CacheEnum.CURRENTUSERMAIL.getCache(), loginUser.getEmail());
        CacheUtil.cachedAuthToken.put(loginUser.getEmail(), token);
    }


    @RequestMapping(value = "report", method = RequestMethod.POST , consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> makeReport(@RequestBody ReportCriterias reportCriterias,
                                             HttpServletResponse response) throws ServletException {

        Optional<Report> report = reportClientService.makeReport(reportCriterias);

        if (report.isPresent() && "APPROVED".equalsIgnoreCase(report.get().getStatus()))
            return new ResponseEntity<String>(HttpStatus.OK);
        else
            return new ResponseEntity<String>(HttpStatus.EXPECTATION_FAILED);
    }
}
