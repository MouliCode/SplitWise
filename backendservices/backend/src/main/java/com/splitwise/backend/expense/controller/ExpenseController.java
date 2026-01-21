package com.splitwise.backend.expense.controller;

import com.splitwise.backend.common.dto.response.ApiResponse;
import com.splitwise.backend.expense.dto.request.CreateExpenseRequest;
import com.splitwise.backend.expense.dto.response.ExpenseResponse;
import com.splitwise.backend.expense.service.ExpenseService;
import com.splitwise.backend.group.entity.Group;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {
   
   private final ExpenseService service;
   
   public ExpenseController (ExpenseService service) {
	  this.service = service;
   }
   
   @PostMapping
   public ApiResponse<Void> create (
		   @Valid @RequestBody CreateExpenseRequest request
   ) {
	  service.createExpense (request);
	  return ApiResponse.success (null);
   }
   
   @PostMapping("/list")
   public ApiResponse<List<ExpenseResponse>> list(
		   @Valid @RequestBody Group group
   ){
	  return ApiResponse.success (service.listGroupExpenses (group.getId ()));
   }
   
}
