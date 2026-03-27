package com.example.MoneyManage.controller;

import com.example.MoneyManage.Service.AuthService;
import com.example.MoneyManage.dto.LoginRequest;
import com.example.MoneyManage.dto.LoginResponse;
import com.example.MoneyManage.dto.SetupResponse;
import com.example.MoneyManage.dto.SignupRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        String result = authService.signup(request);
        if (result.contains("exists")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse result = authService.login(request);
        if (result.getId() == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/setup/{id}")
    public ResponseEntity<SetupResponse> getSetupStatus(@PathVariable("id") Long id) {
        SetupResponse response = authService.getSetup(id);
        if (response == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/change-password/{id}")
    public ResponseEntity<String> changePassword(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String currentPassword = (String) body.get("currentPassword");
        String newPassword = (String) body.get("newPassword");
        String result = authService.changePassword(id, currentPassword, newPassword);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        if ("Incorrect password".equals(result)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }
        return ResponseEntity.ok(result);
    }

    @PutMapping("/change-username/{id}")
    public ResponseEntity<String> changeUsername(@PathVariable Long id, @RequestBody Map<String, Object> body) {
        String newUsername = (String) body.get("username");
        String result = authService.changeUsername(id, newUsername);
        if ("User not found".equals(result)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
        return ResponseEntity.ok(result);
    }
}