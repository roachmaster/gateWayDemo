package com.leonardo.rocha.gatewayDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class GatewayDemoApplication {
	private static final Logger logger = LoggerFactory.getLogger(GatewayDemoApplication.class);

	public static void main(String[] args) {
		logger.info("Updated code");
		SpringApplication.run(GatewayDemoApplication.class, args);
	}

}
