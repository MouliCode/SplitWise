# Database Design – MSSQL

## Database Type
Microsoft SQL Server

---

## Core Tables

users
- id (UUID, PK)
- name
- email (nullable, unique)
- phone (nullable, unique)
- password_hash
- created_at

groups
- id (UUID, PK)
- name
- created_by (FK → users.id)

group_members
- group_id (FK)
- user_id (FK)
- PRIMARY KEY (group_id, user_id)

expenses
- id (UUID, PK)
- description
- amount (DECIMAL(18,2))
- paid_by (FK → users.id)
- group_id (FK → groups.id)
- created_at

expense_splits
- id (UUID, PK)
- expense_id (FK)
- user_id (FK)
- amount_owed (DECIMAL(18,2))

balances
- from_user (FK)
- to_user (FK)
- amount (DECIMAL(18,2))
- PRIMARY KEY (from_user, to_user)

---

## Design Decisions

- UUID used everywhere
- Monetary values use DECIMAL(18,2)
- No cascade deletes on expenses
- Indexes on foreign keys

---

## Scalability Notes

- Read-heavy queries optimized with indexes
- Ready for sharding by group_id
