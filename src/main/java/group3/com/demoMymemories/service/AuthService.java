package group3.com.demoMymemories.service;

import group3.com.demoMymemories.dto.LoginRequest;

import group3.com.demoMymemories.dto.RegisterRequest;

public interface AuthService {
    String register(RegisterRequest request);
    String login(LoginRequest request);
}
