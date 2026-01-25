package com.splitwise.backend.expense.domain;

import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "expense_splits")
public class ExpenseSplit {
   
   @EmbeddedId
   private ExpenseSplitId id;
   
   @MapsId("expenseId")
   @ManyToOne
   @JoinColumn(name = "expense_id")
   private Expense expense;
   
   @MapsId("userId")
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   private BigDecimal amount;
   
   public void setExpense (Expense expense) {
      this.expense = expense;
   }
   
   public void setId (ExpenseSplitId id) {
      this.id = id;
   }
   
   public void setUser (User user) {
      this.user = user;
   }
   
   public void setAmount (BigDecimal amount) {
      this.amount = amount;
   }
   
}
