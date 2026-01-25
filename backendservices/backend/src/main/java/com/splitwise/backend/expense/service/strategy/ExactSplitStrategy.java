package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.SplitRequest;

import java.math.BigDecimal;
import java.util.List;

public class ExactSplitStrategy extends AbstractSplitStrategy implements SplitStrategy{
   
   
   @Override
   public List<ExpenseSplit> split (Expense expense, List<SplitRequest> requests) {
	  
	  BigDecimal total = requests.stream ()
								 .map(SplitRequest::value)
								 .reduce (BigDecimal.ZERO, BigDecimal:: add);
	  
	  if(total.compareTo (expense.getAmount ()) != 0){
		 throw new BusinessException (StandardResponseCode.EXACT_SPLIT_TOTAL_MISMATCH, StandardResponseCode.EXACT_SPLIT_TOTAL_MISMATCH.getMessage ());
	  }
	  return requests.stream ()
					 .map(r ->
								 buildSplit (expense, r.userId (), r.value()))
	  .toList();
   }
}
