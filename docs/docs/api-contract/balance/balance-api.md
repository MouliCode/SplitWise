# Balance API – v1

# Base Path: /api/v1/balances

---

## 1️⃣ Get My Balances

# GET /balances

### Response
```json
{
  "success": true,
  "data": [
    {
      "type": "YOU_OWE",
      "user": "Alice",
      "amount": 300.00
    }
  ],
  "error": null
}
```

2️⃣ Simplify Balances

# POST /balances/simplify

## RESPONSE
```json
{
  "success": true,
  "data": null,
  "error": null
}
```
