package com.report;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * Base test class for MVC-API testing purposes.
 */
@WebAppConfiguration
public class AbstractReportClientControllerTest extends AbstractReportClientTest{


    protected MockMvc mvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;


    protected void setUp(){
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

}
