package com.example.MoneyManage.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "finance")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Finance {

    @Id
    private Long id;


    @Column(name = "pfpid", nullable = false)
    private Long pfpId;

    @Column(name = "bank_balance", nullable = false)
    private Double bankBalance;

    @Column(name = "monthly_budget", nullable = false)
    private Double monthlyBudget;

    @Column(nullable = false)
    private Double income;

    @Column(nullable = false)
    private Double expense;
}