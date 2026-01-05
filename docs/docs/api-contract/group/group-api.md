
---

# 📘 `group-api.md`

# Group API – v1

# Base URL: /api/v1/groups  
# Authentication: JWT required

---

## Create Group

# POST /groups

### Description
Creates a new group and adds creator as member.

### Request

```json
{
  "name": "Goa Trip",
  "memberIds": ["uuid1", "uuid2"]
}
```

### Business Rules

Creator automatically becomes a member

Members must exist

Duplicate members ignored

## SUCCESS RESPONSE
```json
{
  "success": true,
  "data": {
    "groupId": "uuid"
  },
  "error": null
}
```


Business Rules
Creator automatically becomes a member

Members must exist

Duplicate members ignored

## SUCCESS RESPONSE

```json
{
  "success": true,
  "data": {
    "groupId": "uuid"
  },
  "error": null
}
```
### 2️⃣ Get Group Details
# GET /groups/{groupId}

## SUCCESS RESPONSE
```json
{
  "success": true,
  "data": {
    "id": "uuid",
    "name": "Goa Trip",
    "createdBy": "uuid",
    "members": [
      {
        "id": "uuid",
        "name": "Alice"
      }
    ]
  },
  "error": null
}
```
### Error Codes

GROUP_NOT_FOUND

NOT_GROUP_MEMBER