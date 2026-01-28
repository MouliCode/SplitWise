package com.splitwise.backend.expense.repository;

import com.splitwise.backend.expense.domain.ExpenseSplit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ExpenseSplitRepository extends JpaRepository<ExpenseSplit, UUID> {
   
   List<ExpenseSplit> findByExpenseId(UUID expenseId);
}
