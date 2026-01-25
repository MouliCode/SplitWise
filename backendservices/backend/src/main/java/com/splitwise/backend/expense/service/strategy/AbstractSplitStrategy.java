package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class AbstractSplitStrategy {
   
   protected ExpenseSplit buildSplit(
		   Expense expense,
		   UUID userId,
		   BigDecimal amount
   ){
	  
	  ExpenseSplit split = new ExpenseSplit ();
	  split.setExpense (expense);
	  
	  User user = new User();
	  user.setId (userId);
	  split.setUser(user);
	  
	  split.setAmount (amount);
	  return split;
   }
}
