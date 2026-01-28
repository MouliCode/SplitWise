package com.splitwise.backend.balance.service;

import com.splitwise.backend.balance.dto.BalanceResponse;
import com.splitwise.backend.expense.domain.ExpenseSplit;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BalanceService {
   
   void addBalance (UUID fromUserId, UUID toUserId, BigDecimal amount);
   
   List<BalanceResponse> getUserBalances(UUID userId);
   
   void applyExpenseSplits(
           UUID paidBy,
           List<ExpenseSplit> splits
   );
}
