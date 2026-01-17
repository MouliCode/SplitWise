package com.splitwise.backend.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SignupRequest(
		String email,
		String phone,
		
		@Size(max = 100)
		String name,
		
		@NotBlank(message = "Password is required")
		@Size(min = 8, message = "Password must be at least 8 characters")
		String password
) {


}
