package com.vinsguru.r2dbctransaction.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Account {

    @Id
    private Integer id;
    private String userName;
    private Integer balance;

}
