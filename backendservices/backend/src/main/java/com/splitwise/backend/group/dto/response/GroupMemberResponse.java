package com.splitwise.backend.group.dto.response;

import java.util.UUID;

public record GroupMemberResponse(
		UUID userId,
		String name,
		String role
) { }
