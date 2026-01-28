package com.splitwise.backend.expense.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ExpenseSplitResponse(UUID userId, BigDecimal amountOwed) { }
