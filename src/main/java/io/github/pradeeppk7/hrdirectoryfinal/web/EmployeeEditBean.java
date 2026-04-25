package io.github.pradeeppk7.hrdirectoryfinal.web;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
import io.github.pradeeppk7.hrdirectoryfinal.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;

@Named
@RequestScoped
public class EmployeeEditBean {

    @EJB
    private EmployeeService employeeService;

    private Employee employee;
    private Integer empId;

    @PostConstruct
    public void init() {
        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null) {
            empId = Integer.parseInt(idParam);
            employee = employeeService.findById(empId);
        }
    }

    public String save() {
        employeeService.update(employee);

        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Employee updated successfully"));

        return "employees?faces-redirect=true";
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }
}