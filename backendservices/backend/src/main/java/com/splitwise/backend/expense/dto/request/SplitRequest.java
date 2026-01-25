package com.splitwise.backend.expense.dto.request;

import java.math.BigDecimal;
import java.util.UUID;

public record SplitRequest(
		UUID userId,
		BigDecimal value
) {
}
