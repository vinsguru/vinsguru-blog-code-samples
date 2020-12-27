package com.vinsguru.rsocketsecurity;

import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.exceptions.RejectedSetupException;
import io.rsocket.metadata.WellKnownMimeType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class RSocketSecurityApplicationTests {

	private static final MimeType MIME_TYPE = MimeTypeUtils.parseMimeType(WellKnownMimeType.MESSAGE_RSOCKET_AUTHENTICATION.getString());

	@Autowired
	private RSocketRequester.Builder builder;

	@Test
	void wrongSetupCredentials()  {
		RSocketRequester rSocketRequester = builder
				.setupMetadata(Credentials.WRONG_SETUP_CREDENTIALS, MIME_TYPE)
				.tcp("localhost", 6565);

		Mono<Integer> mono = rSocketRequester.route("user-request")
				.metadata(Credentials.CORRECT_USER_CREDENTIALS, MIME_TYPE)
				.data(5)
				.retrieveMono(Integer.class);

		StepVerifier.create(mono)
				.verifyError(RejectedSetupException.class);
	}

	@Test
	void correctSetupCredentials() {
		RSocketRequester rSocketRequester = builder
				.setupMetadata(Credentials.CORRECT_SETUP_CREDENTIALS, MIME_TYPE)
				.tcp("localhost", 6565);

		// correct user credentials
		Mono<Integer> mono1 = rSocketRequester.route("user-request")
				.metadata(Credentials.CORRECT_USER_CREDENTIALS, MIME_TYPE)
				.data(5)
				.retrieveMono(Integer.class);

		StepVerifier.create(mono1)
				.expectNext(10)
				.verifyComplete();

		// wrong user credentials
		Mono<Integer> mono2 = rSocketRequester.route("user-request")
				.metadata(Credentials.WRONG_USER_CREDENTIALS, MIME_TYPE)
				.data(5)
				.retrieveMono(Integer.class);

		StepVerifier.create(mono2)
				.verifyError(ApplicationErrorException.class);
	}

	@Test
	void userPrivilegeTest() throws InterruptedException {
		RSocketRequester rSocketRequester = builder
				.setupMetadata(Credentials.CORRECT_SETUP_CREDENTIALS, MIME_TYPE)
				.tcp("localhost", 6565);

		// correct user credentials
		// but cannot access admin endpoints
		Mono<Integer> mono = rSocketRequester.route("admin-request")
				.metadata(Credentials.CORRECT_USER_CREDENTIALS, MIME_TYPE)
				.data(5)
				.retrieveMono(Integer.class);

		StepVerifier.create(mono)
				.verifyError(ApplicationErrorException.class);

	}


}
