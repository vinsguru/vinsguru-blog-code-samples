package com.vinsguru.inventory.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class OrderInventory {

    @Id
    private int productId;
    private int availableInventory;

}
