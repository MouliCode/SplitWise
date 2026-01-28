package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.SplitRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class PercentSplitStrategy  extends AbstractSplitStrategy
implements SplitStrategy{
   
   
   @Override
   public List<ExpenseSplit> split (Expense expense, List<SplitRequest> requests) {
	  
	  BigDecimal percentSum =
				 requests.stream()
						 .map(SplitRequest::value)
						 .reduce (BigDecimal.ZERO, BigDecimal:: add);
	  
	  if(percentSum.compareTo (BigDecimal.valueOf (100)) != 0){
		 throw new BusinessException (StandardResponseCode.PERCENT_MUST_TOTAL_100, StandardResponseCode.PERCENT_MUST_TOTAL_100.getMessage ());
		 
	  }
	  
	  return requests.stream()
					 .map(r ->{
						BigDecimal amount =
								   expense.getAmount ()
										   .multiply (r.value())
										   .divide (BigDecimal.valueOf (100), 2, RoundingMode.HALF_UP);
						
						return buildSplit (expense, r.userId (), amount);
					 })
					 .toList ();
   }
}
