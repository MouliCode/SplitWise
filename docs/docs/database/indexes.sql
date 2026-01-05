-- =====================================================
-- Indexes for Performance Optimization
-- =====================================================

-- USERS
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_phone ON users(phone);

-- GROUP MEMBERS
CREATE INDEX idx_group_members_user ON group_members(user_id);
CREATE INDEX idx_group_members_group ON group_members(group_id);

-- EXPENSES
CREATE INDEX idx_expenses_group ON expenses(group_id);
CREATE INDEX idx_expenses_paid_by ON expenses(paid_by);
CREATE INDEX idx_expenses_created_at ON expenses(created_at);

-- EXPENSE SPLITS
CREATE INDEX idx_splits_expense ON expense_splits(expense_id);
CREATE INDEX idx_splits_user ON expense_splits(user_id);

-- BALANCES
CREATE INDEX idx_balances_from_user ON balances(from_user);
CREATE INDEX idx_balances_to_user ON balances(to_user);
