package com.splitwise.backend.auth.service;

import com.splitwise.backend.auth.dto.request.LoginRequest;
import com.splitwise.backend.auth.dto.request.SignupRequest;
import com.splitwise.backend.auth.dto.response.AuthResponse;

public interface AuthService {
	
	AuthResponse signup (SignupRequest request);
	
	AuthResponse login (LoginRequest request);
}
