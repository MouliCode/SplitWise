package com.splitwise.backend.expense.service;

import com.splitwise.backend.expense.dto.request.CreateExpenseRequest;
import com.splitwise.backend.expense.dto.response.ExpenseResponse;

import java.util.List;
import java.util.UUID;

public interface ExpenseService {
   
   void createExpense (CreateExpenseRequest request);
   
   List<ExpenseResponse> listGroupExpenses (UUID groupId);
}
