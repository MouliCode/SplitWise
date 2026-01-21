package com.splitwise.backend.expense.service;

import com.splitwise.backend.common.exception.BusinessException;
import com.splitwise.backend.common.exception.StandardResponseCode;
import com.splitwise.backend.domain.entity.User;
import com.splitwise.backend.domain.repository.UserRepository;
import com.splitwise.backend.expense.domain.Expense;
import com.splitwise.backend.expense.dto.request.CreateExpenseRequest;
import com.splitwise.backend.expense.dto.response.ExpenseResponse;
import com.splitwise.backend.expense.repository.ExpenseRepository;
import com.splitwise.backend.group.entity.Group;
import com.splitwise.backend.group.repository.GroupMemberRepository;
import com.splitwise.backend.group.repository.GroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {
   
   private final ExpenseRepository     expenseRepository;
   private final GroupRepository       groupRepository;
   private final GroupMemberRepository groupMemberRepository;
   private final UserRepository        userRepository;
   
   
   public ExpenseServiceImpl (
		   ExpenseRepository expenseRepository,
		   GroupRepository groupRepository,
		   UserRepository userRepository,
		   GroupMemberRepository groupMemberRepository
   ) {
	  this.expenseRepository     = expenseRepository;
	  this.groupRepository       = groupRepository;
	  this.groupMemberRepository = groupMemberRepository;
	  this.userRepository        = userRepository;
   }
   
   
   @Override
   public void createExpense (CreateExpenseRequest request) {
	  
	  Group group = groupRepository.findById(request.groupId ())
							.orElseThrow (()-> new BusinessException (
									StandardResponseCode.GROUP_NOT_FOUND, StandardResponseCode.GROUP_NOT_FOUND.getMessage ()
							));
	  
	  User payer = userRepository.findById (request.paidBy ())
						   .orElseThrow (() -> new BusinessException (StandardResponseCode.USER_NOT_FOUND,
								   StandardResponseCode.USER_NOT_FOUND.getMessage ()
						   ));
	  
	  boolean isMember = groupMemberRepository
								 .existsByGroup_IdAndUser_Id (group.getId (), payer.getId ());
	  
	  if(!isMember){
		 throw new BusinessException (StandardResponseCode.PAYER_NOT_IN_GROUP,
				 StandardResponseCode.PAYER_NOT_IN_GROUP.getMessage ());
	  }
	  
	  Expense expense = new Expense ();
	  expense.setGroup (group);
	  expense.setAmount (request.amount ());
	  expense.setDescription (request.description ());
	  expense.setPaidBy (payer);
	  
	  expenseRepository.save(expense);
   }
   
   @Override
   public List<ExpenseResponse> listGroupExpenses (UUID groupId) {
	
	  return expenseRepository.findByGroup_Id (groupId)
					 .stream ()
					 .map(e -> new ExpenseResponse(
							 e.getId (),
							 e.getGroup ().getId (),
							 e.getPaidBy ().getId(),
							 e.getAmount (),
							 e.getDescription (),
							 e.getCreatedAt ()
					 ))
					 .toList();
   }
}
