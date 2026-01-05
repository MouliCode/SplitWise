# Auth API – v1

Base Path: /api/v1/auth

---

## 1️⃣ Register User

# POST /auth/register

## REQUEST
```json
{
  "name": "John Doe",
  "email": "john@mail.com",
  "phone": "+919876543210",
  "password": "Secret@123"
}
```


Validation Rules

name → required

password → required

Either email OR phone must be present

Both can be present

Email must be unique if provided

Phone must be unique if provided

## SUCCESS RESPONSE

```json
{
  "success": true,
  "data": {
    "userId": "uuid"
  },
  "error": null
}
```

## Error Codes
Code	        Reason
EMAIL_EXISTS	Email already registered
PHONE_EXISTS	Phone already registered
INVALID_INPUT	Neither email nor phone provided


## Login User

# POST /auth/login

Login with Email
```json
{
  "email": "john@mail.com",
  "password": "Secret@123"
}
```

Login with Phone
```json
{
  "phone": "+919876543210",
  "password": "Secret@123"
}
```

Validation Rules

Either email OR phone is required

Password is mandatory

## SUCCESS RESPONSE
```json
{
  "success": true,
  "data": {
    "token": "JWT_TOKEN",
    "expiresIn": 3600
  },
  "error": null
}
```

### Error Codes

USER_NOT_FOUND

INVALID_CREDENTIALS


---
