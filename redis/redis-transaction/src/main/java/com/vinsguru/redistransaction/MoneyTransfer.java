package com.vinsguru.redistransaction;

import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor(staticName = "of")
public class MoneyTransfer implements SessionCallback<List<Object>> {

    public static final String ACCOUNT = "account";
    private final int fromAccountId;
    private final int toAccountId;
    private final int amount;

    @Override
    public <K, V> List<Object> execute(RedisOperations<K, V> redisOperations) throws DataAccessException {
        var operations = (RedisTemplate<Object, Object>) redisOperations;
        var hashOperations = operations.opsForHash();
        var fromAccount = (Account) hashOperations.get(ACCOUNT, fromAccountId);
        var toAccount = (Account) hashOperations.get(ACCOUNT, toAccountId);
        if(Objects.nonNull(fromAccount) && Objects.nonNull(toAccount) && fromAccount.getBalance() >= amount){
            try{
                operations.multi();
                fromAccount.setBalance(fromAccount.getBalance() - amount);
                toAccount.setBalance(toAccount.getBalance() + amount);
                hashOperations.put(ACCOUNT, fromAccountId, fromAccount);
                hashOperations.put(ACCOUNT, toAccountId, toAccount);
                return operations.exec();
            }catch (Exception e){
                operations.discard();
            }
        }
        return Collections.emptyList();
    }
}
