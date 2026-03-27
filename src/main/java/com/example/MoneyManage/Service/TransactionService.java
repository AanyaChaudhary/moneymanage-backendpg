package com.example.MoneyManage.Service;

import com.example.MoneyManage.Repo.TransactionRepo;
import com.example.MoneyManage.Repo.Repo;
import com.example.MoneyManage.Repo.FinanceRepo;
import com.example.MoneyManage.dto.TransactionRequest;
import com.example.MoneyManage.entity.TestEntity;
import com.example.MoneyManage.entity.Transaction;
import com.example.MoneyManage.entity.TransactionId;
import com.example.MoneyManage.entity.Finance;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class TransactionService {

    private final TransactionRepo transactionRepo;
    private final Repo userRepo;
    private final FinanceRepo financeRepo;

    public TransactionService(TransactionRepo transactionRepo, Repo userRepo, FinanceRepo financeRepo) {
        this.transactionRepo = transactionRepo;
        this.userRepo = userRepo;
        this.financeRepo = financeRepo;
    }

    public String addTransaction(TransactionRequest request) {

        TestEntity user = userRepo.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Unsuccessful"));

        TransactionId transactionId = new TransactionId(
                request.getDate() != null ? request.getDate() : LocalDate.now(),
                request.getTime() != null ? request.getTime() : LocalTime.now()
        );

        Transaction transaction = Transaction.builder()
                .idd(request.getId())
                .tid(transactionId)
                .amount(request.getAmount())
                .description(request.getDescription())
                .transactionType(request.getTransactionType())
                .category(request.getCategory())
                .build();

        transactionRepo.save(transaction);

        // Update Finance entity
        Finance finance = financeRepo.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("Finance record not found"));

        if ("income".equalsIgnoreCase(request.getTransactionType())) {
            finance.setIncome(finance.getIncome() + request.getAmount());
            finance.setBankBalance(finance.getBankBalance() + request.getAmount());
        } else if ("expense".equalsIgnoreCase(request.getTransactionType())) {
            finance.setExpense(finance.getExpense() + request.getAmount());
            finance.setBankBalance(finance.getBankBalance() - request.getAmount());
        }

        financeRepo.save(finance);

        return "successful";
    }

    public List<Transaction> getCurrentMonthTransactions(Long id) {

        LocalDate startOfMonth = LocalDate.now().withDayOfMonth(1);
        LocalDate endOfMonth = LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth());

        return transactionRepo.findByIddAndTid_DateBetween(id, startOfMonth, endOfMonth);
    }
}