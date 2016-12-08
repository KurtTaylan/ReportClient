package com.report.controller;

import com.report.dto.login.User;
import com.report.service.ReportClientService;
import com.report.util.CacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ReportClientController {



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
            CacheUtil.cachedAuthToken.put(loginUser.getEmail(), token);
            return new ResponseEntity<String>(HttpStatus.OK);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
    }


}
