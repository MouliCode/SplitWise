package com.splitwise.backend.expense.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record CreateExpenseResponse(
		UUID expenseId,
		String description,
		BigDecimal amount,
		UUID paidBy,
		UUID groupId,
		String splitType,
		LocalDateTime createdAt,
		List<ExpenseSplitResponse> splits

) {
}
