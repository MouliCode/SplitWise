package com.splitwise.backend.settlement.service;

import com.splitwise.backend.balance.domain.Balance;
import com.splitwise.backend.balance.repository.BalanceRepository;
import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.settlement.domain.Settlement;
import com.splitwise.backend.settlement.repository.SettlementRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@Transactional
public class SettlementServiceImpl implements SettlementService{
  
   private final BalanceRepository    balanceRepository ;
   private final SettlementRepository settlementReposiory;
   
   public SettlementServiceImpl(BalanceRepository balanceRepository,
								SettlementRepository settlementReposiory){
	  this.balanceRepository = balanceRepository;
	  this.settlementReposiory = settlementReposiory;
   }
   @Override
   public void settle (UUID fromUserId, UUID toUserId, BigDecimal amount) {
	  
	  Balance balance = balanceRepository.findByIdFromUserAndIdToUser (fromUserId, toUserId)
								.orElseThrow (() -> new BusinessException (StandardResponseCode.NO_BALANCE,
										StandardResponseCode.NO_BALANCE.getMessage ()));
	  
	  Settlement settlement = new Settlement (
			  UUID.randomUUID (),
			  balance.getFromUser (),
			  balance.getToUser (),
			  amount
	  );
	  
	  settlementReposiory.save(settlement);
	  
	  BigDecimal remaining = balance.getAmount ().subtract (amount);
	  
	  if(remaining.compareTo (BigDecimal.ZERO) == 0){
		 balanceRepository.delete (balance);
	  }else{
		 balance.setAmount(remaining);
		 balanceRepository.save(balance);
	  }
	  
	  
	  
   }
   
  
}
