package com.example.MoneyManage.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Transaction {

    @EmbeddedId
    private TransactionId tid;

    private Long idd;

    @Column(nullable = false)
    private Double amount;

    private String description;

    private String transactionType;

    private String category;
}

