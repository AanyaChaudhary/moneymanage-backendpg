package com.example.MoneyManage.Repo;

import com.example.MoneyManage.entity.Transaction;
import com.example.MoneyManage.entity.TransactionId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, TransactionId> {

    List<Transaction> findByIdd(Long idd);

    List<Transaction> findByIddAndTid_DateBetween(Long idd, LocalDate start, LocalDate end);
}