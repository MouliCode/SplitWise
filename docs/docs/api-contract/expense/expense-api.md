# Expense API – v1

# Base Path: /api/v1/expenses

---

## 1️⃣ Add Expense

# POST /expenses

### REQUEST
```json
{
  "description": "Dinner",
  "amount": 1200.00,
  "paidBy": "uuid",
  "groupId": "uuid",
  "splitType": "EQUAL",
  "participants": ["uuid1", "uuid2"]
}
```


## SUCCESS RESPONSE
```json
{
  "success": true,
  "data": {
    "expenseId": "uuid"
  },
  "error": null
}
```

Error Codes

INVALID_SPLIT

NOT_GROUP_MEMBER