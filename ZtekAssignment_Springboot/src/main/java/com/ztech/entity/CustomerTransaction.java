package com.ztech.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "customer_transaction_seq", sequenceName = "customer_transaction_seq",
allocationSize = 1)
public class CustomerTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_transaction_seq")
    private Long id;
    private String customerName;

    @Column(nullable = false)
    private Long amount;
    private java.util.Date transactionDate;


}
