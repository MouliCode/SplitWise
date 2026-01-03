# System Design – Splitwise Clone

## Overview
This system is a Splitwise-like expense sharing application built using:

- Backend: Java + Spring Boot
- Frontend: Angular
- Database: Microsoft SQL Server (MSSQL)
- Architecture Style: Component-based (Vertical Slice)
- Authentication: JWT

---

## High-Level Architecture

Angular Frontend
    ↓ (REST APIs)
Spring Boot Backend
    ├── Auth Component
    ├── User Component
    ├── Group Component
    ├── Expense Component
    └── Balance Component
    ↓
MSSQL Database

---

## Key Design Principles

- API-first development
- DTO ≠ Entity separation
- Thin controllers
- Business logic only in services
- Component ownership
- Stateless backend

---

## Why Component-Based Architecture

- High cohesion
- Low coupling
- Easy scalability
- Microservice-ready
- Parallel team development

---

## Non-Functional Requirements

- Scalability
- Security
- Maintainability
- Testability
- Clear documentation
