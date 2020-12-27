package com.vinsguru.rsocketsecurity;

import org.springframework.security.rsocket.metadata.UsernamePasswordMetadata;

public class Credentials {

    public static final UsernamePasswordMetadata WRONG_SETUP_CREDENTIALS = new UsernamePasswordMetadata("client", "wrong");
    public static final UsernamePasswordMetadata CORRECT_SETUP_CREDENTIALS = new UsernamePasswordMetadata("client", "client");
    public static final UsernamePasswordMetadata WRONG_USER_CREDENTIALS = new UsernamePasswordMetadata("user", "wrong");
    public static final UsernamePasswordMetadata CORRECT_USER_CREDENTIALS = new UsernamePasswordMetadata("user", "user");
    public static final UsernamePasswordMetadata WRONG_ADMIN_CREDENTIALS = new UsernamePasswordMetadata("admin", "wrong");
    public static final UsernamePasswordMetadata CORRECT_ADMIN_CREDENTIALS = new UsernamePasswordMetadata("admin", "admin");

}
