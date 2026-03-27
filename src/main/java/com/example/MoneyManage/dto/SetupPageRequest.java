package com.example.MoneyManage.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SetupPageRequest {

    private Long id;
    private Long PFPid;
    private Double bankBalance;
    private Double monthlyBudget;
    private Double income;
    private Double expense;
    private Boolean setup;
}
