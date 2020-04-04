package com.vinsguru.webclient.reactivewebclient;

import com.vinsguru.webclient.reactivewebclient.model.Post;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

@SpringBootTest
class ReactiveWebclientApplicationTests {

	private static final String BASE_URL = "http://localhost:3000/";
	private static WebClient webClient;

	@BeforeAll
	static void setWebClient(){
		webClient = WebClient.builder()
				.baseUrl(BASE_URL)
				.build();
	}

	// http://localhost:3000/posts
	@Test
	void get() throws InterruptedException {
		webClient
				.get()
				.uri("/posts")
				.retrieve()
				.bodyToMono(Post[].class)
				.flatMapIterable(Arrays::asList)
				.subscribe(System.out::println);
	}

	// http://localhost:3000/posts
	@Test
	void post() {
		// create a new post object
		Post post2 = new Post();
		post2.setId(2);
		post2.setAuthor("vinsguru");
		post2.setTitle("project reactor");
		//post via webclient
		webClient
				.post()
				.uri("/posts")
				.body(BodyInserters.fromValue(post2))
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(System.out::println);
	}

	// http://localhost:3000/posts/2
	@Test
	void patch() {
		//patch via webclient
		webClient
				.patch()
				.uri("/posts/2")
				.body(BodyInserters.fromFormData("title", "webclient"))
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(System.out::println);
	}

	// http://localhost:3000/posts/2
	@Test
	void delete() {
		webClient
				.delete()
				.uri("/posts/2")
				.retrieve()
				.bodyToMono(String.class)
				.subscribe(System.out::println);
	}

	@AfterAll
	static void await() throws Exception{
		Thread.sleep(5000);
	}


}
