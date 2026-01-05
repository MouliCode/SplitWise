-- =====================================================
-- Splitwise Clone - Database Schema (MSSQL)
-- =====================================================

-- USERS
CREATE TABLE users (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    email NVARCHAR(150) UNIQUE NULL,
    phone NVARCHAR(20) UNIQUE NULL,
    password_hash NVARCHAR(255) NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),

    CONSTRAINT chk_user_identity
    CHECK (
        email IS NOT NULL OR phone IS NOT NULL
    )
);

-- GROUPS
CREATE TABLE groups (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    name NVARCHAR(100) NOT NULL,
    created_by UNIQUEIDENTIFIER NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),

    CONSTRAINT fk_groups_created_by
    FOREIGN KEY (created_by) REFERENCES users(id)
);

-- GROUP MEMBERS
CREATE TABLE group_members (
    group_id UNIQUEIDENTIFIER NOT NULL,
    user_id UNIQUEIDENTIFIER NOT NULL,
    joined_at DATETIME2 DEFAULT SYSDATETIME(),

    PRIMARY KEY (group_id, user_id),

    CONSTRAINT fk_group_members_group
        FOREIGN KEY (group_id) REFERENCES groups(id),

    CONSTRAINT fk_group_members_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- EXPENSES
CREATE TABLE expenses (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    description NVARCHAR(255) NOT NULL,
    amount DECIMAL(18,2) NOT NULL,
    paid_by UNIQUEIDENTIFIER NOT NULL,
    group_id UNIQUEIDENTIFIER NOT NULL,
    created_at DATETIME2 DEFAULT SYSDATETIME(),

    CONSTRAINT fk_expenses_paid_by
        FOREIGN KEY (paid_by) REFERENCES users(id),

    CONSTRAINT fk_expenses_group
        FOREIGN KEY (group_id) REFERENCES groups(id)
);

-- EXPENSE SPLITS
CREATE TABLE expense_splits (
    id UNIQUEIDENTIFIER NOT NULL PRIMARY KEY,
    expense_id UNIQUEIDENTIFIER NOT NULL,
    user_id UNIQUEIDENTIFIER NOT NULL,
    amount_owed DECIMAL(18,2) NOT NULL,

    CONSTRAINT fk_splits_expense
        FOREIGN KEY (expense_id) REFERENCES expenses(id),

    CONSTRAINT fk_splits_user
        FOREIGN KEY (user_id) REFERENCES users(id)
);

-- BALANCES
CREATE TABLE balances (
    from_user UNIQUEIDENTIFIER NOT NULL,
    to_user UNIQUEIDENTIFIER NOT NULL,
    amount DECIMAL(18,2) NOT NULL,

    PRIMARY KEY (from_user, to_user),

    CONSTRAINT fk_balance_from
        FOREIGN KEY (from_user) REFERENCES users(id),

    CONSTRAINT fk_balance_to
        FOREIGN KEY (to_user) REFERENCES users(id)
);
