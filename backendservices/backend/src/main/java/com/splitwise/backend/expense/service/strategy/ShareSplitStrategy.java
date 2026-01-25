package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.SplitRequest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class ShareSplitStrategy extends AbstractSplitStrategy implements SplitStrategy{
   
   
   @Override
   public List<ExpenseSplit> split (Expense expense, List<SplitRequest> requests) {
	  
	  BigDecimal totalShares =
				 requests.stream()
						 .map(SplitRequest::value)
						 .reduce (BigDecimal.ZERO, BigDecimal::add);
	  
	  
	  return requests.stream()
					 .map(r ->{
						BigDecimal amount =
								   expense.getAmount ()
										   .multiply (r.value())
										   .divide (totalShares, 2, RoundingMode.HALF_UP);
						
						return buildSplit (expense, r.userId (), amount);
					 })
					 .toList ();
			  
   }
}
