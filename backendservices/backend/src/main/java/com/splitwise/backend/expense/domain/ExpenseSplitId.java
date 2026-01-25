package com.splitwise.backend.expense.domain;

import jakarta.persistence.Embeddable;

import java.util.Objects;
import java.util.UUID;

@Embeddable
public class ExpenseSplitId {
   
   private UUID expenseId;
   private UUID userId;
   
   @Override
   public boolean equals (Object o) {
      if (!(o instanceof ExpenseSplitId that)) { return false; }
	  return Objects.equals (expenseId, that.expenseId) && Objects.equals (userId, that.userId);
   }
   
   @Override
   public int hashCode () {
      return Objects.hash (expenseId, userId);
   }
}
