package com.example.MoneyManage.Repo;

import com.example.MoneyManage.entity.Finance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FinanceRepo extends JpaRepository<Finance, Long> {

    Optional<Finance> findByid(Long id);

    Optional<Finance> findByBankBalance(Double bankBalance);
}