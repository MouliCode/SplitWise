package com.splitwise.backend.auth.controller;


import com.splitwise.backend.auth.dto.request.LoginRequest;
import com.splitwise.backend.auth.dto.request.SignupRequest;
import com.splitwise.backend.auth.dto.response.AuthResponse;
import com.splitwise.backend.auth.service.AuthService;
import com.splitwise.backend.common.dto.response.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private final AuthService service;
	
	public AuthController (AuthService service) {
		this.service = service;
	}
	
	@PostMapping("/signup")
	public ApiResponse<AuthResponse> signup (@Valid @RequestBody SignupRequest request) {
		return ApiResponse.success (service.signup (request));
	}
	
	@PostMapping("/login")
	public ApiResponse<AuthResponse> login (@Valid @RequestBody LoginRequest request) {
		return ApiResponse.success (service.login (request));
	}
	
}
