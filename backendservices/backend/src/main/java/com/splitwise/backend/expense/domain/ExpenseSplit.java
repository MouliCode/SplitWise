package com.splitwise.backend.expense.domain;

import com.splitwise.backend.domain.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "expense_splits")
public class ExpenseSplit {
   
   @Id
   @GeneratedValue
   private UUID id;
   
   @ManyToOne
   @JoinColumn(name = "expense_id")
   private Expense expense;
   
   
   @ManyToOne
   @JoinColumn(name = "user_id")
   private User user;
   
   private BigDecimal amount;
   
   public void setExpense (Expense expense) {
	  this.expense = expense;
   }
   
   public void setId (UUID id) {
	  this.id = id;
   }
   
   public void setUser (User user) {
	  this.user = user;
   }
   
   public void setAmount (BigDecimal amount) {
	  this.amount = amount;
   }
   
   public UUID getId () {
	  return id;
   }
   
   public Expense getExpense () {
	  return expense;
   }
   
   public User getUser () {
	  return user;
   }
   
   public BigDecimal getAmount () {
	  return amount;
   }
}
