package com.robinlb99.legalserviceportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.robinlb99.legalserviceportal.domain")
public class LegalserviceportalApplication {

	public static void main(String[] args) {
		SpringApplication.run(LegalserviceportalApplication.class, args);
	}

}
