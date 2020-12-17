package com.vinsguru.reactivesecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.Map;

@Configuration
public class UserDB {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public Map<String, UserDetails> map(){
        return Map.of(
                "user", User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build(),
                "admin", User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build(),
                "any", User.withUsername("any").password(passwordEncoder.encode("any")).authorities(Collections.emptyList()).build()
        );
    }

}
