package com.example.MoneyManage.controller;

import com.example.MoneyManage.Service.TransactionService;
import com.example.MoneyManage.dto.TransactionRequest;
import com.example.MoneyManage.entity.Transaction;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @PostMapping
    public String addTransaction(@RequestBody TransactionRequest request) {
        return transactionService.addTransaction(request);
    }

    @GetMapping("/{id}")
    public List<Transaction> getCurrentMonthTransactions(@PathVariable Long id) {
        return transactionService.getCurrentMonthTransactions(id);
    }

    }




