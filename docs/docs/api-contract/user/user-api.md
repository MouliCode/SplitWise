
# 📘 `user-api.md`

# User API – v1

# Base URL: /api/v1/users  
# Authentication: JWT required

---

## Get Current User

# GET /users/me

### Description
Returns profile of currently authenticated user.

### SUCCESS RESPONSE
```json
{
  "success": true,
  "data": {
    "id": "uuid",
    "name": "John Doe",
    "email": "john@mail.com",
    "phone": "+919876543210"
  },
  "error": null
}
```
### Notes

Either email or phone may be null

User identity always comes from JWT

No userId accepted from client

### Error Codes

UNAUTHORIZED