package io.github.pradeeppk7.hrdirectoryfinal.service;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class EmployeeService {

    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    public List<Employee> getAllEmployees() {
        return em.createQuery(
                "SELECT e FROM Employee e ORDER BY e.empId",
                Employee.class
        ).getResultList();
    }

    public List<Employee> getEmployeesByDepartment(Integer deptId) {
        return em.createQuery(
                        "SELECT e FROM Employee e WHERE e.department.deptId = :deptId ORDER BY e.empId",
                        Employee.class
                )
                .setParameter("deptId", deptId)
                .getResultList();
    }

    public List<Employee> searchByName(String keyword) {
        return em.createQuery(
                        "SELECT e FROM Employee e WHERE LOWER(e.fullName) LIKE :kw ORDER BY e.empId",
                        Employee.class
                )
                .setParameter("kw", "%" + keyword.toLowerCase() + "%")
                .getResultList();
    }

    public Employee findById(Integer id) {
        return em.find(Employee.class, id);
    }

    @RolesAllowed("ADMIN")
    public void update(Employee employee) {
        em.merge(employee);
    }
}
