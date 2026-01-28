package com.splitwise.backend.settlement.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record SettlementRequest(
		UUID fromUserId,
		UUID toUserId,
		BigDecimal amount
) { }
