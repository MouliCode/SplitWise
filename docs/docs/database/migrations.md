# Database Migration Strategy

## Tool Recommendation
Flyway (preferred) or Liquibase

---

## Migration Versioning

Migration files follow this format:

V1__init_schema.sql  
V2__add_phone_column.sql  
V3__add_indexes.sql  

---

## Example Migration Flow

### V1__init_schema.sql
- Create core tables
- Create constraints

### V2__add_phone_column.sql
- Add phone column to users
- Add unique constraint

### V3__add_indexes.sql
- Add performance indexes

---

## Rules
- Never modify old migration files
- Always create a new version
- Migrations must be backward-compatible
- Production DB changes only via migration

---

## Environment Strategy
| Environment | Migration |
|------------|----------|
| Local | Auto-run |
| QA | Manual approval |
| Prod | Strict approval |

---

## Rollback Strategy
- Prefer forward-fix migrations
- Avoid destructive rollbacks
