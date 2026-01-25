package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.SplitRequest;

import java.util.List;

public interface SplitStrategy {
   
   List<ExpenseSplit> split(Expense expense, List<SplitRequest> requests);
}
