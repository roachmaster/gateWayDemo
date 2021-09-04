package com.leonardo.rocha.gatewayDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.leonardo.rocha.gatewayDemo.DemoApiClient;
import com.leonardo.rocha.gatewayDemo.model.Post;
import com.leonardo.rocha.gatewayDemo.model.Todo;

import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
@RequestMapping("/")
public class GatewayController {
	private static final Logger logger = LoggerFactory.getLogger(GatewayController.class);
	
	@Autowired 
	private DemoApiClient demoApiClient;
	
    @RequestMapping(value = "todos/{id}", method = RequestMethod.GET)
    public Mono<Todo> getTodos(@PathVariable int id) {
    	logger.info("Get todos requested: {}", id);
		return demoApiClient.getTodos(id);
    }   
    
    @RequestMapping(value = "posts/{id}", method = RequestMethod.GET)
    public Mono<Post> sendPost(@PathVariable int id) {
    	logger.info("Sending Post requested");
		return demoApiClient.sendPost(id);
    }  
    
}
