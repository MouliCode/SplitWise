# Common API Rules

## Headers
### Authorization: Bearer <JWT_TOKEN>
#### Content-Type: application/json

## Standard Response Wrapper

### SUCCESS
```json
{
  "success": true,
  "data": {},
  "error": null
}
```

### Error
```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "ERROR_CODE",
    "message": "Human readable message"
  }
}
```

## Global Rules
- Either email OR phone is required (not both mandatory)
- At least one identifier must be present
- UUID everywhere
- Money → DECIMAL(18,2)
- Backend owns all validations & calculations
