package com.report.service;


import com.report.dto.login.User;
import com.report.dto.report.Report;
import com.report.dto.report.ReportCriterias;

import java.util.Optional;

public interface ReportClientService {

    Optional<String> login(User loginUser);

    Optional<Report> makeReport(ReportCriterias reportCriterias);
}
