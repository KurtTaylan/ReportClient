package com.report.service;


import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;
import com.report.dto.transaction.TransactionResult;

import java.util.Optional;

public interface ReportClientService {

    Optional<String> login(User loginUser);

    Optional<Report> makeReport(ReportCriterias reportCriterias);

    Optional<TransactionResult> fetchTransaction(String transactionId);

    Optional<String> listTransactions(String transactionId);
}
