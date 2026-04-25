package io.github.pradeeppk7.hrdirectoryfinal.service;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
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

    public void update(Employee employee) {
        em.merge(employee);
    }
}
