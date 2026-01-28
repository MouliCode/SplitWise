package com.splitwise.backend.expense.service.strategy;

import com.splitwise.backend.expense.SplitType;
import org.springframework.stereotype.Component;

@Component
public class SplitStrategyFactory {
   
   private final EqualSplitStrategy equalSplitStrategy;
   private final ExactSplitStrategy exactSplitStrategy;
   private final PercentSplitStrategy percentSplitStrategy;
   private final ShareSplitStrategy shareSplitStrategy;
   
   public SplitStrategyFactory(
		   EqualSplitStrategy equalSplitStrategy,
		   ExactSplitStrategy exactSplitStrategy,
		   PercentSplitStrategy percentSplitStrategy,
		   ShareSplitStrategy shareSplitStrategy
   ){
	  this.equalSplitStrategy = equalSplitStrategy;
	  this.exactSplitStrategy = exactSplitStrategy;
	  this.percentSplitStrategy = percentSplitStrategy;
	  this.shareSplitStrategy = shareSplitStrategy;
   }
   
   public SplitStrategy getStrategy(SplitType splitType){
	  
	  return switch (splitType){
		 case EQUAL -> equalSplitStrategy;
		 case EXACT -> exactSplitStrategy;
		 case PERCENTAGE -> percentSplitStrategy;
		 case SHARE -> shareSplitStrategy;
		 
		 default -> throw new IllegalArgumentException (
				 "Unsupported split type : " + splitType
		 );
	  };
   }
   
}
