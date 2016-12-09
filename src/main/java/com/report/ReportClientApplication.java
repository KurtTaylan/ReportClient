package com.report;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class ReportClientApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReportClientApplication.class, args);
	}

}
