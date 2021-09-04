package com.leonardo.rocha.gatewayDemo;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriBuilder;

import com.leonardo.rocha.gatewayDemo.model.Post;
import com.leonardo.rocha.gatewayDemo.model.Todo;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DemoApiClient {
	@Autowired
	private WebClient  webClient;
	
	public Flux<Todo> getTodos(){
		return webClient.get().uri("/todos").retrieve().bodyToFlux(Todo.class);
	}
	
	public Mono<Todo> getTodos(int id){
		return webClient.get().uri(
				uriBuilder -> getTodoPath(uriBuilder, id))
				.retrieve()
				.bodyToMono(Todo.class);
	}
	
	private URI getTodoPath(UriBuilder uriBuilder, int id) {
		return uriBuilder.path("/todos/{index}").build(id);
	}
	
	public Mono<Post> sendPost(int i) {
		Post post = new Post(i, "foo", "bar", 123);
		
		return webClient.put().uri("/posts/" + i)
		.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
		.body(Mono.just(post), Post.class)
		.retrieve()
		.bodyToMono(Post.class);
	}

}
