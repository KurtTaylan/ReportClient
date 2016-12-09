package com.report.controller;

import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.dto.transaction.TransactionResult;
import com.report.service.ReportClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class ReportClientController {


    @Autowired
    ReportClientService reportClientService;



    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestParam(value = "email", defaultValue ="") String email,
                                        @RequestParam(value = "password", defaultValue ="") String password) throws ServletException {

        User loginUser = new User(email,password);
        Optional<String> userToken = reportClientService.login(loginUser);

        if (userToken.isPresent()) {
            return new ResponseEntity<String>(HttpStatus.OK);
        }else
            return new ResponseEntity<String>(HttpStatus.UNAUTHORIZED);
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


    @RequestMapping(value = "report", method = RequestMethod.POST , consumes = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody TransactionResult fetchTransaction(@RequestParam String transactionId) throws ServletException {

        Optional<TransactionResult> transactionResult = reportClientService.fetchTransaction(transactionId);
        return transactionResult.get();
    }
}
