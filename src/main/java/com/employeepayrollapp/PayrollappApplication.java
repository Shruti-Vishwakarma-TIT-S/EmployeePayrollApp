package com.employeepayrollapp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayrollappApplication {

	public static final Logger logger= LoggerFactory.getLogger(PayrollappApplication.class);
	public static void main(String[] args) {
		logger.info("Application starting...");
		SpringApplication.run(PayrollappApplication.class, args);
		logger.info("Application started.");
	}

}
