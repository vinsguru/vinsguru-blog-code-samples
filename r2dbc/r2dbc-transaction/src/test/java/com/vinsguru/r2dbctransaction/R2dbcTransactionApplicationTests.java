package com.vinsguru.r2dbctransaction;

import com.vinsguru.r2dbctransaction.dto.DepositRequest;
import com.vinsguru.r2dbctransaction.entity.Account;
import com.vinsguru.r2dbctransaction.repository.AccountRepository;
import com.vinsguru.r2dbctransaction.service.BankService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class R2dbcTransactionApplicationTests {

	@Value("classpath:init.sql")
	private Resource initSql;

	@Autowired
	private R2dbcEntityTemplate entityTemplate;

	@Autowired
	private BankService bankService;

	@Autowired
	private AccountRepository repository;

	@BeforeAll
	public void initDB() throws IOException {
		String query = StreamUtils.copyToString(initSql.getInputStream(), StandardCharsets.UTF_8);
		Mono<Void> mono = this.entityTemplate
				.getDatabaseClient()
				.sql(query)
				.then();

		StepVerifier.create(mono)
				.verifyComplete();
	}

	@Test
	@Order(1)
	void transactionSuccess() {
		DepositRequest request = DepositRequest.create(1, 500);
		Mono<Account> mono = this.bankService.deposit(request)
				.then(getAccountDetails(request));
		StepVerifier.create(mono)
				.expectNextMatches(ac -> ac.getBalance() == 500)
				.verifyComplete();
	}

	@Test
	@Order(2)
	void transactionFailure() {
		DepositRequest request = DepositRequest.create(1, 99);
		Mono<Account> mono = this.bankService.deposit(request)
				.onErrorResume(ex -> Mono.empty())
				.then(getAccountDetails(request));
		StepVerifier.create(mono)
				.expectNextMatches(ac -> ac.getBalance() == 500)
				.verifyComplete();
	}

	private Mono<Account> getAccountDetails(DepositRequest request){
		return this.repository.findById(request.getAccount())
					.doOnNext(System.out::println);
	}

}
