package io.github.pradeeppk7.hrdirectoryfinal.web;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Department;
import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
import io.github.pradeeppk7.hrdirectoryfinal.service.DepartmentService;
import io.github.pradeeppk7.hrdirectoryfinal.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;

@Named
@RequestScoped
public class EmployeeBean {

    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    private List<Employee> employees;
    private List<Department> departments;

    private String keyword;
    private Integer deptId;

    @PostConstruct
    public void init() {
        departments = departmentService.getAllDepartments();
        employees = employeeService.getAllEmployees();
    }

    public void search() {
        if (keyword == null || keyword.trim().isEmpty()) {
            employees = employeeService.getAllEmployees();
        } else {
            employees = employeeService.searchByName(keyword);
        }
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}