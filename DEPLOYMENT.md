# Deployment Guide (Payara)

## Prerequisites
- JDK 17
- Maven 3.9+ (or Maven Wrapper included in repo)
- Payara Server 6+
- Existing HR schema with tables:
  - `Department(dept_id, dept_name)`
  - `Employee(emp_id, full_name, title, dept_id, salary, hire_date)`

## Configure datasource in Payara
Configure a JDBC connection pool and JDBC resource named exactly:
- `jdbc/HrDS`

`persistence.xml` expects this JTA datasource name.

## Build and deploy
1. Build WAR:
   ```powershell
   .\mvnw.cmd clean package
   ```
2. Deploy:
   - Admin Console: Applications -> Deploy -> select `target/hr-directory-final.war`
   - or use `asadmin deploy target/hr-directory-final.war`

## Smoke test
1. Open `http://localhost:8080/hr-directory-final/departments.xhtml`
2. Click **View Employees** for any department and confirm filtered employees appear.
3. Open `http://localhost:8080/hr-directory-final/employees.xhtml` and test search.
4. Login as ADMIN and open `editEmployee.xhtml?id=<emp_id>`, update title/salary, save.
5. Confirm success message and updated values on employee list.

