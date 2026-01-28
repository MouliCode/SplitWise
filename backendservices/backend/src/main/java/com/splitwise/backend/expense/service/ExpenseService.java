package com.splitwise.backend.expense.service;

import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.CreateExpenseRequest;
import com.splitwise.backend.expense.dto.response.CreateExpenseResponse;
import com.splitwise.backend.expense.dto.response.ExpenseResponse;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
   
   CreateExpenseResponse createExpense (CreateExpenseRequest request);
   
   List<ExpenseResponse> listGroupExpenses (UUID groupId);
   
   void updateBalances(UUID payerId, List<ExpenseSplit> splits);
}
