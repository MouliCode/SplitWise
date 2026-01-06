
---

# 👥 `docs/api-contract/group/group-members-api.md`

# Group Members API – v1

---

## 1️⃣ Add Member to Group

# POST /groups/{groupId}/members

### REQUEST
```json
{
  "userId": "uuid"
}
```


### Rules

Only group creator can add members

User must exist

User must not already be a member


---

## 2️⃣ Remove Member from Group

# DELETE /groups/{groupId}/members/{userId}

### Rules
- Creator cannot be removed
- Pending balances must be settled