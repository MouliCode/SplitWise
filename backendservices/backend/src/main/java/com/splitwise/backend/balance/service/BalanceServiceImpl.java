package com.splitwise.backend.balance.service;

import com.splitwise.backend.balance.domain.Balance;
import com.splitwise.backend.balance.dto.BalanceResponse;
import com.splitwise.backend.balance.repository.BalanceRepository;
import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class BalanceServiceImpl implements BalanceService {
   
   private final BalanceRepository balanceRepository;
   private final UserRepository    userRepository;
   
   public BalanceServiceImpl (BalanceRepository balanceRepository,
							  UserRepository userRepository) {
	  this.balanceRepository = balanceRepository;
	  this.userRepository    = userRepository;
   }
   
   
   @Override
   public void addBalance (UUID fromUserId, UUID toUserId, BigDecimal amount) {
	  
	  if (fromUserId.equals (toUserId)) { return; }
	  
	  User fromUser =
			  userRepository.findById (fromUserId)
					  .orElseThrow (() -> new BusinessException (StandardResponseCode.USER_NOT_FOUND,
							  StandardResponseCode.USER_NOT_FOUND.getMessage ()));
	  
	  User toUser = userRepository.findById (toUserId)
							.orElseThrow (() -> new BusinessException (StandardResponseCode.USER_NOT_FOUND,
									StandardResponseCode.USER_NOT_FOUND.getMessage ()));
	  
	  Balance balance = balanceRepository
								.findByIdFromUserAndIdToUser (fromUserId, toUserId)
								.orElseGet (() -> new Balance (fromUser, toUser, BigDecimal.ZERO));
	  
	  balance.addAmount (amount);
	  
	  
	  if (balance.getAmount ().compareTo (BigDecimal.ZERO) < 0) {
		 Balance reverse = balanceRepository
								   .findByIdFromUserAndIdToUser (toUserId, fromUserId)
								   .orElseGet (() -> new Balance (toUser, fromUser, BigDecimal.ZERO));
		 
		 reverse.addAmount (balance.getAmount ().abs ());
		 balanceRepository.delete (balance);
		 balanceRepository.save(reverse);
	  }else{
		 balanceRepository.save(balance);
	  }
	  
	  
   }
   
   @Override
   public List<BalanceResponse> getUserBalances (UUID userId) {
	  return balanceRepository.findById_FromUser (userId)
					 .stream()
					 .map(b -> new BalanceResponse (
							 b.getId().getFromUser (),
							 b.getId ().getToUser (),
							 b.getAmount ()
					 ))
					 .toList();
   }
   
   @Override
   public void applyExpenseSplits(
		   UUID paidBy,
		   List<ExpenseSplit> splits
   ){
	  for(ExpenseSplit split : splits){
		 
		 UUID from  = split.getUser ().getId (); //owes
		 BigDecimal amount = split.getAmount ();
		 
		 if(from.equals (paidBy)) continue;
		 
		 addBalance (from, paidBy, amount);
	  }
   }
}
