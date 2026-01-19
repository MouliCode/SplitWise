package com.splitwise.backend.group.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record GroupResponse(
		UUID id,
		String name,
		String description,
		UUID createdBy,
		LocalDateTime createdAt
) { }
