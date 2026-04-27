# Security (RBAC)

This application uses Payara container-managed security with two roles:
- `USER`
- `ADMIN`

Configured in `src/main/webapp/WEB-INF/web.xml` with BASIC auth and `file` realm.

## Protected resources
- `/departments.xhtml`: `USER`, `ADMIN`
- `/employees.xhtml`: `USER`, `ADMIN`
- `/editEmployee.xhtml`: `ADMIN` only

## Runtime behavior
- USER can access department and employee list pages.
- USER cannot access edit page URL.
- ADMIN can access edit page and save updates.
- Write operation is also service-guarded with `@RolesAllowed("ADMIN")` on update method.

## Quick test procedure
1. Login as USER (example class account in group `USER`).
2. Open:
   - `/departments.xhtml` -> allowed
   - `/employees.xhtml` -> allowed
   - `/editEmployee.xhtml?id=1` -> denied (403 or auth failure)
3. Login as ADMIN (example class account in group `ADMIN`).
4. Open `/editEmployee.xhtml?id=1` -> allowed.
5. Change title/salary and save -> allowed; success message shown.

Do not store credentials in this repository.

