package io.github.pradeeppk7.hrdirectoryfinal.service;
import io.github.pradeeppk7.hrdirectoryfinal.entity.Department;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class DepartmentService {

    @PersistenceContext(unitName = "HRPU")
    private EntityManager em;

    public List<Department> getAllDepartments() {
        return em.createQuery(
                "SELECT d FROM Department d ORDER BY d.deptId",
                Department.class
        ).getResultList();
    }
}