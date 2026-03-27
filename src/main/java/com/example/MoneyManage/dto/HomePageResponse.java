package com.example.MoneyManage.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomePageResponse {

    private Double bankBalance;
    private Double monthlyBudget;
    private Double income;
    private Double expense;
    private Long pfpID;
    private String username;
}