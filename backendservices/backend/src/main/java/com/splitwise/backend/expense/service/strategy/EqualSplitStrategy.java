package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.SplitRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Component
public class EqualSplitStrategy  extends AbstractSplitStrategy implements SplitStrategy {
   
   
   @Override
   public List<ExpenseSplit> split (Expense expense, List<SplitRequest> requests) {
	
	  int n = requests.size();
	  
	  BigDecimal perHead =
				 expense.getAmount ()
						 .divide (BigDecimal.valueOf (n), 2, RoundingMode.HALF_UP);
	  
	  return requests.stream()
					 .map(r ->
								 buildSplit(expense, r.userId (), perHead))
					 .toList();
   }
}
