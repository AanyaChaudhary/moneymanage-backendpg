package com.example.MoneyManage.controller;

import com.example.MoneyManage.Service.FinanceService;
import com.example.MoneyManage.dto.SetupPageRequest;
import com.example.MoneyManage.dto.HomePageResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @PostMapping("/setup")
    public ResponseEntity<String> saveSetup(@RequestBody SetupPageRequest request) {
        String result = financeService.saveSetupData(request);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/home/{id}")
    public ResponseEntity<HomePageResponse> getHomePage(@PathVariable Long id) {
        return financeService.getHomePageData(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/budget/{id}")
    public ResponseEntity<String> updateBudget(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Double monthlyBudget = ((Number) body.get("monthlyBudget")).doubleValue();
        String result = financeService.updateBudget(id, monthlyBudget);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/bank-balance/{id}")
    public ResponseEntity<String> updateBankBalance(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Double bankBalance = ((Number) body.get("bankBalance")).doubleValue();
        String result = financeService.updateBankBalance(id, bankBalance);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update/pfp/{id}")
    public ResponseEntity<String> updatePfp(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        Long pfpId = ((Number) body.get("pfpId")).longValue();
        String result = financeService.updatePfp(id, pfpId);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reset/expense/{id}")
    public ResponseEntity<String> resetExpense(@PathVariable Long id) {
        String result = financeService.resetExpense(id);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/reset/income/{id}")
    public ResponseEntity<String> resetIncome(@PathVariable Long id) {
        String result = financeService.resetIncome(id);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}