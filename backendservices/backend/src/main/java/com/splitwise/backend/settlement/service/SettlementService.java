package com.splitwise.backend.settlement.service;

import java.math.BigDecimal;
import java.util.UUID;

public interface SettlementService {
   
   void settle (UUID fromUserId, UUID toUserId, BigDecimal amount);
   
}
