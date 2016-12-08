package com.report;

import com.report.service.ReportClientService;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Base test class for common behaviors of different test suites.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ReportClientApplication.class)
public abstract class AbstractReportClientTest {


    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    protected ReportClientService reportClientService;

}
