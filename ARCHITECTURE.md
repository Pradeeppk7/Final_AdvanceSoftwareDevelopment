# Architecture Summary

- Facelets pages are the presentation layer: `departments.xhtml`, `employees.xhtml`, `editEmployee.xhtml`.
- JSF backing beans are thin UI coordinators in `io.github.pradeeppk7.hrdirectoryfinal.web`.
- `DepartmentBean` loads department data for display only.
- `EmployeeBean` handles employee listing, search input, and department filter input.
- `EmployeeEditBean` loads one employee by request parameter and handles save action.
- Business logic and transaction boundaries are in stateless EJB services.
- `DepartmentService` provides department read operations via JPQL.
- `EmployeeService` provides employee read/search/filter/update operations.
- JPA `EntityManager` is only used inside service layer classes.
- JPQL queries use parameters (example: `:kw`, `:deptId`) and avoid dynamic query concatenation.
- Entity model consists of `Department` and `Employee` with `@ManyToOne` mapping from employee to department.
- Persistence unit `HRPU` is JTA-based and uses datasource `jdbc/HrDS`.
- Declarative RBAC is configured in `web.xml` for page-level access control.
- Additional service-layer RBAC is applied to write operation using `@RolesAllowed("ADMIN")`.
- Validation is handled in JSF form components (`required` and minimum salary constraint).

