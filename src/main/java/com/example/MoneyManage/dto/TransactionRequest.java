package com.example.MoneyManage.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TransactionRequest {

    private Long id;
    private Double amount;
    private String description;
    private String transactionType;
    private String category;


    private LocalDate date;
    private LocalTime time;
}

