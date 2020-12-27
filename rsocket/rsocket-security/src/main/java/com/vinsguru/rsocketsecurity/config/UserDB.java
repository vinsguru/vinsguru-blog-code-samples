package com.vinsguru.rsocketsecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

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
                "client", User.withUsername("client").password(passwordEncoder.encode("client")).roles("TRUSTED_CLIENT").build()
        );
    }

}
