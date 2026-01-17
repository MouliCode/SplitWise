package com.splitwise.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
		String email,
		String phone,
		
		@NotBlank(message = "password is required")
		String password

) { }
