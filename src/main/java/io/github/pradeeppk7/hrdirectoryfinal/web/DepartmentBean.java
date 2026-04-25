package io.github.pradeeppk7.hrdirectoryfinal.web;


import io.github.pradeeppk7.hrdirectoryfinal.entity.Department;
import io.github.pradeeppk7.hrdirectoryfinal.service.DepartmentService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class DepartmentBean {

    @EJB
    private DepartmentService departmentService;

    private List<Department> departments;

    @PostConstruct
    public void init() {
        departments = departmentService.getAllDepartments();
    }

    public List<Department> getDepartments() {
        return departments;
    }
}