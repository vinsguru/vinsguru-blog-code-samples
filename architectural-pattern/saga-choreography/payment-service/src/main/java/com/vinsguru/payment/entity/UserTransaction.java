package com.vinsguru.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class UserTransaction {

    @Id
    private UUID orderId;
    private int userId;
    private int amount;

}
