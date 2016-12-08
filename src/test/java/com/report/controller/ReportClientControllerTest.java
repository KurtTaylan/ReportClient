package com.report.controller;

import com.report.AbstractReportClientControllerTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;


/**
 * API Test Cases for importing file and fetching records.
 *  <p>
 *  Test cases:
 *  -TC1: Fetching person data by parameter 'name'
 *      - INPUT 'gülsüm' has 2 records, INPUT 'yalnız' has 1 record
 *  <p>
 *  -TC2: End-to-end Importing batch data, test data is contact.json that where is under resources directory.
 *  <p>
 *  <p>
 *  <p>
 */
@Transactional
public class ReportClientControllerTest extends AbstractReportClientControllerTest {



    @Override
    @Before
    public void setUp() {
        super.setUp();
    }


    @Test
    public void testLogin() throws Exception {

    }


    @Test
    public void testMakeReport() throws Exception {

    }


    @Test
    public void testListReports() throws Exception {

    }

}


