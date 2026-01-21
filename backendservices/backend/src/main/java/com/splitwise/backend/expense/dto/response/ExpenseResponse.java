package com.splitwise.backend.expense.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ExpenseResponse(
		UUID id,
		UUID groupId,
		UUID paidBy,
		BigDecimal amount,
		String description,
		LocalDateTime createdAt
) { }
