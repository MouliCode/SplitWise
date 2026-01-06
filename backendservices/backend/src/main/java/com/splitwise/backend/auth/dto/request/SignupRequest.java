package com.splitwise.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public class SignupRequest {
	
	@NotBlank
	private String name;
	
	private String email;
	private String phone;
	
	@NotBlank
	private String password;
	
	public String getName () {
		return name;
	}
	
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
