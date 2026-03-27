package com.example.MoneyManage.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

// Embeddable composite key
@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionId {
    private LocalDate date;
    private LocalTime time;
}
