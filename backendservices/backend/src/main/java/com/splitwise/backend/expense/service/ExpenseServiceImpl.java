package com.splitwise.backend.expense.service;

import com.splitwise.backend.balance.domain.Balance;
import com.splitwise.backend.balance.domain.BalanceId;
import com.splitwise.backend.balance.repository.BalanceRepository;
import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.domain.ExpenseSplit;
import com.splitwise.backend.expense.dto.request.CreateExpenseRequest;
import com.splitwise.backend.expense.dto.response.CreateExpenseResponse;
import com.splitwise.backend.expense.dto.response.ExpenseResponse;
import com.splitwise.backend.expense.dto.response.ExpenseSplitResponse;
import com.splitwise.backend.expense.repository.ExpenseRepository;
import com.splitwise.backend.expense.repository.ExpenseSplitRepository;
import com.splitwise.backend.expense.service.strategy.SplitStrategy;
import com.splitwise.backend.expense.service.strategy.SplitStrategyFactory;
import com.splitwise.backend.group.entity.Group;
import com.splitwise.backend.group.repository.GroupMemberRepository;
import com.splitwise.backend.group.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
   
   private final ExpenseRepository      expenseRepository;
   private final GroupRepository        groupRepository;
   private final GroupMemberRepository  groupMemberRepository;
   private final UserRepository         userRepository;
   private final ExpenseSplitRepository expenseSplitRepository;
   private final BalanceRepository      balanceRepository;
   
   private final SplitStrategyFactory splitStrategyFactory;
   
   
   public ExpenseServiceImpl (
		   ExpenseRepository expenseRepository,
		   GroupRepository groupRepository,
		   UserRepository userRepository,
		   GroupMemberRepository groupMemberRepository,
		   SplitStrategyFactory splitStrategyFactory,
		   ExpenseSplitRepository expenseSplitRepository,
		   BalanceRepository balanceRepository
   ) {
	  this.expenseRepository      = expenseRepository;
	  this.groupRepository        = groupRepository;
	  this.groupMemberRepository  = groupMemberRepository;
	  this.userRepository         = userRepository;
	  this.splitStrategyFactory   = splitStrategyFactory;
	  this.expenseSplitRepository = expenseSplitRepository;
	  this.balanceRepository      = balanceRepository;
   }
   
   
   @Override
   public CreateExpenseResponse createExpense (CreateExpenseRequest request) {
	  
	  Group group = groupRepository.findById (request.groupId ())
							.orElseThrow (() -> new BusinessException (
									StandardResponseCode.GROUP_NOT_FOUND,
									StandardResponseCode.GROUP_NOT_FOUND.getMessage ()
							));
	  
	  User payer = userRepository.findById (request.paidBy ())
						   .orElseThrow (() -> new BusinessException (StandardResponseCode.USER_NOT_FOUND,
								   StandardResponseCode.USER_NOT_FOUND.getMessage ()
						   ));
	  
	  boolean isMember = groupMemberRepository
								 .existsByGroup_IdAndUser_Id (group.getId (), payer.getId ());
	  
	  if (!isMember) {
		 throw new BusinessException (StandardResponseCode.PAYER_NOT_IN_GROUP,
				 StandardResponseCode.PAYER_NOT_IN_GROUP.getMessage ());
	  }
	  
	  Expense expense = new Expense ();
	  expense.setGroup (group);
	  expense.setAmount (request.amount ());
	  expense.setDescription (request.description ());
	  expense.setPaidBy (payer);
	  expense.setSplitType (request.splitType ());
	  
	  expenseRepository.save (expense);
	  
	  SplitStrategy strategy = splitStrategyFactory.getStrategy (request.splitType ());
	  
	  List<ExpenseSplit> splits = strategy.split (
			  expense,
			  request.splits ()
	  );
	  
	  expenseSplitRepository.saveAll (splits);
	  
	  updateBalances (expense.getPaidBy ().getId (), splits);
	  
	  List<ExpenseSplitResponse> splitResponses =
			  splits.stream ()
					  .map (split -> new ExpenseSplitResponse (
							  split.getUser ().getId (),
							  split.getAmount ()
					  ))
					  .toList ();
	  
	  return new CreateExpenseResponse (
			  expense.getId (),
			  expense.getDescription (),
			  expense.getAmount (),
			  expense.getPaidBy ().getId (),
			  expense.getGroup ().getId (),
			  expense.getSplitType ().toString (),
			  expense.getCreatedAt (),
			  splitResponses
	  );
	  
	  
   }
   
   @Override
   public List<ExpenseResponse> listGroupExpenses (UUID groupId) {
	  
	  return expenseRepository.findByGroup_Id (groupId)
					 .stream ()
					 .map (e -> new ExpenseResponse (
							 e.getId (),
							 e.getGroup ().getId (),
							 e.getPaidBy ().getId (),
							 e.getAmount (),
							 e.getDescription (),
							 e.getCreatedAt ()
					 ))
					 .toList ();
   }
   
   @Override
   public void updateBalances (UUID payerId, List<ExpenseSplit> splits) {
	  
	  for (ExpenseSplit split : splits) {
		 
		 UUID owingUserId = split.getUser ().getId ();
		 
		 if (owingUserId.equals (payerId)) {
			continue;
		 }
		 
		 BigDecimal amount = split.getAmount ();
		 
		 BalanceId id = new BalanceId (owingUserId, payerId);
		 
		 Balance balance = balanceRepository.findById (id)
								   .orElseGet (() -> {
									  Balance b = new Balance ();
									  b.setId (id);
									  
									  b.setFromUser (split.getUser ()); // owingUser
									  b.setToUser (split.getExpense ().getPaidBy ());// payer
									  
									  b.setAmount (BigDecimal.ZERO);
									  return b;
								   });
		 balance.setAmount (balance.getAmount ().add (amount));
		 balanceRepository.save (balance);
		 
	  }
   }
}
