package com.vinsguru.redistransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootApplication
public class RedisTransactionApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(RedisTransactionApplication.class, args);
	}

	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;

	@Override
	public void run(String... args) throws Exception {

		// initialize some accounts
		Account account1 = Account.of(1, 100);
		Account account2 = Account.of(2, 20);
		this.redisTemplate.opsForHash().put(MoneyTransfer.ACCOUNT, account1.getUserId(), account1);
		this.redisTemplate.opsForHash().put(MoneyTransfer.ACCOUNT, account2.getUserId(), account2);

		// do the transaction
		this.redisTemplate.execute(MoneyTransfer.of(account1.getUserId(),account2.getUserId(), 30));

		// print the result
		System.out.println(this.redisTemplate.opsForHash().get(MoneyTransfer.ACCOUNT, account1.getUserId()));
		System.out.println(this.redisTemplate.opsForHash().get(MoneyTransfer.ACCOUNT, account2.getUserId()));

	}
}
