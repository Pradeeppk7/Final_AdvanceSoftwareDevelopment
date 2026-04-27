package io.github.pradeeppk7.hrdirectoryfinal.web;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
import io.github.pradeeppk7.hrdirectoryfinal.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@ViewScoped
public class EmployeeEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    private Employee employee;
    private Integer empId;
    private boolean employeeFound;

    @PostConstruct
    public void init() {
        employee = new Employee();
        employeeFound = false;

        String idParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("id");

        if (idParam != null && !idParam.isBlank()) {
            try {
                empId = Integer.parseInt(idParam);
                Employee loaded = employeeService.findById(empId);
                if (loaded != null) {
                    employee = loaded;
                    employeeFound = true;
                }
            } catch (NumberFormatException ignored) {
                employeeFound = false;
            }
        }
    }

    public String save() {
        if (!employeeFound) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot save: employee not loaded", null));
            return "employees?faces-redirect=true";
        }

        employeeService.update(employee);

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
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

    public boolean isEmployeeFound() {
        return employeeFound;
    }
}
