package com.splitwise.backend.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest (
		@NotBlank(message = "Name cannot be Empty")
		String name,
		
		String phone,
		String email,
		String password
) { }
