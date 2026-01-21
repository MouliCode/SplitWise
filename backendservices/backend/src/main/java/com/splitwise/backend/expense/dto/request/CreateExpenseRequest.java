package com.splitwise.backend.expense.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseRequest(
		
		@NotNull UUID groupId,
		
		@NotNull
		UUID paidBy,
		
		@NotNull
		@Positive
		BigDecimal amount,
		
		String description

) {
}
