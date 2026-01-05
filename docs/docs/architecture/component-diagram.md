# Component Diagram

## Components Overview

Auth Component
- User registration
- Login
- JWT generation

User Component
- Fetch logged-in user profile

Group Component
- Group creation
- Member management

Expense Component
- Expense creation
- Split calculation

Balance Component
- Balance aggregation
- Debt simplification

---

## Component Dependencies

Auth → User → Group → Expense → Balance

---

## Shared Modules

common/
- dto
- exception
- security
- config

---

## Dependency Rules

- Components may depend on `common`
- Components must not directly depend on each other’s internals
- Communication only via service interfaces
