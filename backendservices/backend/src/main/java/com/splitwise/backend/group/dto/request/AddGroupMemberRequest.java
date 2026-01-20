package com.splitwise.backend.group.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record AddGroupMemberRequest(
		
		@NotNull(message = "Group ID is required")
		UUID groupId,
		
		@NotNull(message = "User ID is required")
		UUID userId
) { }
