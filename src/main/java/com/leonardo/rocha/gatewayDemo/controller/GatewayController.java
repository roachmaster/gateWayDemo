package com.leonardo.rocha.gatewayDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/")
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);

	
    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<String> getTest() {
		String response = "Hello, World!";
    	logger.info("Response: {}", response);
		return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
