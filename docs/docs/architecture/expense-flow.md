# Expense Flow

## Add Expense Flow

Client
 → POST /expenses
 → ExpenseController
 → ExpenseService
 → SplitStrategyFactory
 → SplitStrategy (EQUAL / EXACT / PERCENTAGE)
 → ExpenseRepository
 → ExpenseSplitRepository
 → BalanceService
 → MSSQL

---

## Split Strategy Design

Strategy Pattern used to calculate splits:

- EqualSplitStrategy
- ExactSplitStrategy
- PercentageSplitStrategy

Each strategy:
- Validates input
- Calculates per-user share

---

## Transaction Management

- Expense creation is atomic
- All inserts happen in a single transaction
- Rollback on any failure

---

## Why Backend Calculates Splits

- Prevents manipulation
- Ensures consistency
- Single source of truth
