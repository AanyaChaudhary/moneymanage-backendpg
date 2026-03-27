package com.example.MoneyManage.Service;

import com.example.MoneyManage.Repo.FinanceRepo;
import com.example.MoneyManage.Repo.Repo;
import com.example.MoneyManage.dto.HomePageResponse;
import com.example.MoneyManage.entity.TestEntity;
import com.example.MoneyManage.dto.SetupPageRequest;
import com.example.MoneyManage.entity.Finance;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinanceService {

    private final FinanceRepo financeRepo;
    private final Repo userRepo;

    public FinanceService(FinanceRepo financeRepo, Repo userRepo) {
        this.financeRepo = financeRepo;
        this.userRepo = userRepo;
    }

    public String saveSetupData(SetupPageRequest request) {
        Optional<TestEntity> userOptional = userRepo.findById(request.getId());
        if (userOptional.isEmpty()) {
            return "Unsuccessful";
        }
        TestEntity user = userOptional.get();
        user.setSetup(request.getSetup());
        userRepo.save(user);
        System.out.println(request.getId());
        Finance finance = Finance.builder()
                .id(request.getId())
                .pfpId(request.getPFPid())
                .bankBalance(request.getBankBalance())
                .monthlyBudget(request.getMonthlyBudget())
                .income(request.getIncome())
                .expense(request.getExpense())
                .build();
        financeRepo.save(finance);
        return "successful";
    }

    public Optional<HomePageResponse> getHomePageData(Long userId) {
        Optional<Finance> financeOptional = financeRepo.findByid(userId);
        Optional<TestEntity> userOptional = userRepo.findById(userId);

        if (financeOptional.isEmpty() || userOptional.isEmpty()) {
            return Optional.empty();
        }

        Finance finance = financeOptional.get();
        TestEntity user = userOptional.get();

        return Optional.of(new HomePageResponse(
                finance.getBankBalance(),
                finance.getMonthlyBudget(),
                finance.getIncome(),
                finance.getExpense(),
                finance.getPfpId(),
                user.getUsername()
        ));
    }

    public String updateBudget(Long id, Double monthlyBudget) {
        Optional<Finance> financeOptional = financeRepo.findByid(id);
        if (financeOptional.isEmpty()) {
            return "User not found";
        }
        Finance finance = financeOptional.get();
        finance.setMonthlyBudget(monthlyBudget);
        financeRepo.save(finance);
        return "successful";
    }

    public String updateBankBalance(Long id, Double bankBalance) {
        Optional<Finance> financeOptional = financeRepo.findByid(id);
        if (financeOptional.isEmpty()) {
            return "User not found";
        }
        Finance finance = financeOptional.get();
        finance.setBankBalance(bankBalance);
        financeRepo.save(finance);
        return "successful";
    }

    public String updatePfp(Long id, Long pfpId) {
        Optional<Finance> financeOptional = financeRepo.findByid(id);
        if (financeOptional.isEmpty()) {
            return "User not found";
        }
        Finance finance = financeOptional.get();
        finance.setPfpId(pfpId);
        financeRepo.save(finance);
        return "successful";
    }

    public String resetExpense(Long id) {
        Optional<Finance> financeOptional = financeRepo.findByid(id);
        if (financeOptional.isEmpty()) {
            return "User not found";
        }
        Finance finance = financeOptional.get();
        finance.setExpense(0.0);
        financeRepo.save(finance);
        return "successful";
    }

    public String resetIncome(Long id) {
        Optional<Finance> financeOptional = financeRepo.findByid(id);
        if (financeOptional.isEmpty()) {
            return "User not found";
        }
        Finance finance = financeOptional.get();
        finance.setIncome(0.0);
        financeRepo.save(finance);
        return "successful";
    }
}