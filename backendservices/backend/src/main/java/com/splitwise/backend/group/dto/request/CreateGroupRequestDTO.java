package com.splitwise.backend.group.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateGroupRequestDTO(
		@NotBlank(message = "Group name is requried")
		@Size(max = 100, message = "Group name must not exceed 100 characters")
		String name,
		
		@Size(max = 255, message = "Description must not exceed 255 characters")
		String description
) { }
