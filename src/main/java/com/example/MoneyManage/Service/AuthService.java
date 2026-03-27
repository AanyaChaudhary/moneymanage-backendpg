package com.example.MoneyManage.Service;

import com.example.MoneyManage.entity.TestEntity;
import com.example.MoneyManage.Repo.Repo;
import com.example.MoneyManage.dto.LoginRequest;
import com.example.MoneyManage.dto.LoginResponse;
import com.example.MoneyManage.dto.SetupResponse;
import com.example.MoneyManage.dto.SignupRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final Repo repo;

    public AuthService(Repo repo) {
        this.repo = repo;
    }

    public String signup(SignupRequest request) {
        if (repo.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }
        TestEntity user = TestEntity.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(request.getPassword())
                .setup(request.getSetup())
                .build();
        repo.save(user);
        return "Successful";
    }

    public LoginResponse login(LoginRequest request) {
        Optional<TestEntity> userOptional = repo.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return new LoginResponse(null, "unsuccessful");
        }
        TestEntity user = userOptional.get();
        if (!user.getPassword().equals(request.getPassword())) {
            return new LoginResponse(null, "unsuccessful");
        }
        return new LoginResponse(user.getId(), "successful");
    }

    public SetupResponse getSetup(Long id) {
        Optional<TestEntity> userOptional = repo.findById(id);
        if (userOptional.isEmpty()) {
            return null;
        }
        TestEntity user = userOptional.get();
        return new SetupResponse(user.getId(), user.getSetup());
    }

    public String changePassword(Long id, String currentPassword, String newPassword) {
        Optional<TestEntity> userOptional = repo.findById(id);
        if (userOptional.isEmpty()) {
            return "User not found";
        }
        TestEntity user = userOptional.get();
        if (!user.getPassword().equals(currentPassword)) {
            return "Incorrect password";
        }
        user.setPassword(newPassword);
        repo.save(user);
        return "successful";
    }

    public String changeUsername(Long id, String newUsername) {
        Optional<TestEntity> userOptional = repo.findById(id);
        if (userOptional.isEmpty()) {
            return "User not found";
        }
        TestEntity user = userOptional.get();
        user.setUsername(newUsername);
        repo.save(user);
        return "successful";
    }
}