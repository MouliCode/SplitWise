# Authentication Flow

## Registration Flow

Client
 → POST /auth/register
 → AuthController
 → AuthService
 → UserRepository
 → MSSQL

---

## Login Flow

Client
 → POST /auth/login
 → AuthController
 → AuthService
 → Password validation
 → JWT generation
 → Client receives token

---

## Authenticated Request Flow

Client
 → Sends JWT in Authorization header
 → JWT Filter validates token
 → UserContext populated
 → Controller → Service → Repository

---

## Security Notes

- Passwords stored as BCrypt hashes
- JWT is stateless
- User identity derived only from JWT
