package com.splitwise.backend.expense.dto.request;

import com.splitwise.backend.expense.SplitType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateExpenseRequest(
		
		@NotNull UUID groupId,
		
		@NotNull
		UUID paidBy,
		
		@NotNull
		@Positive
		BigDecimal amount,
		
		String description,
		
		SplitType splitType,
		
		List<SplitRequest> splist

) {
}
