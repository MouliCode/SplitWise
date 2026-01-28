package com.splitwise.backend.balance.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record BalanceResponse(
		UUID fromUserId,
		UUID toUserId,
		BigDecimal amount
) {
}
