package com.splitwise.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
	
	private String email;
	private String phone;
	
	@NotBlank
	private String password;
	
	public String getEmail () {
		return email;
	}
	
	public String getPhone () {
		return phone;
	}
	
	public String getPassword () {
		return password;
	}
	
}
