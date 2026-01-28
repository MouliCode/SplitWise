package com.splitwise.backend.settlement.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record SettlementResponse(
		UUID id,
		UUID formUserId,
		UUID toUserId,
		BigDecimal amount,
		LocalDateTime createdAt
) {
}
