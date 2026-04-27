# HR Directory Final Project

This is a Jakarta EE web application for HR directory management using:
- JSF/Facelets for the UI
- JPA/JPQL for persistence
- Stateless EJB service layer for business logic and transactions
- Payara container-managed security (RBAC)

## Features
- Department list page showing `dept_id` and `dept_name`
- Employee list page showing `emp_id`, `full_name`, `title`, `department`, `salary`, `hire_date`
- Employee filtering by department (via department link and dropdown)
- Employee search by name using JPQL named parameter
- ADMIN-only edit flow for employee `title` and `salary`
- JSF validation (`title` required, `salary >= 0`)

## Screenshots
### Department List (USER View)
![Department List USER View](docs/screenshots/department-list.png)

### Employee List (USER View)
![Employee List USER View](docs/screenshots/employee-list-user-view.png)

### Employee List (ADMIN View)
![Employee List ADMIN View](docs/screenshots/employee-list-admin-view.png)

### Add Employee (ADMIN View)
![Add Employee ADMIN View](docs/screenshots/add-employee-admin-view.png)

### Edit Employee (ADMIN View)
![Edit Employee ADMIN View](docs/screenshots/edit-employee-admin-view.png)

### USER Denied ADMIN Access (403)
![USER Denied ADMIN Access](docs/screenshots/user-denied-admin-access.png)

### SQL Workbench Data Verification
![SQL Workbench Data Verification](docs/screenshots/sql-workbench-data-proof.png)

### Server Ping Succeeded (Payara JDBC Pool)
![Server Ping Succeeded](docs/screenshots/server-ping-succeeded.png)

## Run
1. Configure Payara JDBC resource `jdbc/HrDS` that points to your HR database.
2. Build WAR:
   ```powershell
   .\mvnw.cmd clean package
   ```
3. Deploy `target/hr-directory-final.war` to Payara.
4. Open:
   - `http://localhost:8080/hr-directory-final/departments.xhtml`
   - `http://localhost:8080/hr-directory-final/employees.xhtml`

## Key URLs
- Department list: `/hr-directory-final/departments.xhtml`
- Employee list: `/hr-directory-final/employees.xhtml`
- Edit employee (ADMIN): `/hr-directory-final/editEmployee.xhtml?id=<emp_id>`
