package com.vinsguru.webfluxprotobuf;

import com.vinsguru.models.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class WebfluxProtobufApplicationTests {

	private WebClient webClient;

	@BeforeAll
	private void setWebClient(){
		this.webClient = WebClient.builder()
				.baseUrl("http://localhost:8080/person/")
				.build();
	}

	@Test
	void getPersonTest() {
		StepVerifier.create(this.getPerson(1))
				.expectNextCount(1)
				.verifyComplete();
	}

	@Test
	void savePersonTest() {

		// create person
		Person sam = Person.newBuilder()
				.setName("sam")
				.setAge(10)
				.build();

		// save
		Mono<Void> mono = this.webClient.post()
				.bodyValue(sam)
				.retrieve()
				.bodyToMono(Void.class);

		// confirm if it saves
		StepVerifier.create(mono)
				.verifyComplete();

		//  retrieve and verify
		StepVerifier.create(this.getPerson(2))
				.expectNextMatches(p -> p.getAge() == 10)
				.verifyComplete();

	}

	private Mono<Person> getPerson(int id){
		return this.webClient.get()
				.uri("{id}", id)
				.retrieve()
				.bodyToMono(Person.class)
				.doOnNext(p -> System.out.println(p.toString()));
	}

}
