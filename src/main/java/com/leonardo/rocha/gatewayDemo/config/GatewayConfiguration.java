package com.leonardo.rocha.gatewayDemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfiguration {
	@Value(value = "https://jsonplaceholder.typicode.com/")
	public String baseUri;

	@Bean
	public WebClient webclient() {
    	return WebClient.builder()
    	        .baseUrl(baseUri)
    	        .build();
	}
}
