package com.vinsguru.r2dbctransaction.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor(staticName = "create")
public class MoneyDepositEvent {

    @Id
    private Integer id;
    private Integer accountNumber;
    private Integer amount;

}
