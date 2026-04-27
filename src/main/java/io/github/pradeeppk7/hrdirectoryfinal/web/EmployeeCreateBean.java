package io.github.pradeeppk7.hrdirectoryfinal.web;

import io.github.pradeeppk7.hrdirectoryfinal.entity.Department;
import io.github.pradeeppk7.hrdirectoryfinal.entity.Employee;
import io.github.pradeeppk7.hrdirectoryfinal.service.DepartmentService;
import io.github.pradeeppk7.hrdirectoryfinal.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

@Named
@ViewScoped
public class EmployeeCreateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    private Employee employee;
    private Integer selectedDeptId;
    private List<Department> departments;
    private String hireDateInput;

    @PostConstruct
    public void init() {
        employee = new Employee();
        departments = departmentService.getAllDepartments();
        hireDateInput = "";
    }

    public String save() {
        Department selectedDepartment = departmentService.findById(selectedDeptId);
        if (selectedDepartment == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid department", null));
            return null;
        }

        employee.setDepartment(selectedDepartment);

        if (hireDateInput == null || hireDateInput.isBlank()) {
            employee.setHireDate(LocalDate.now());
        } else {
            try {
                employee.setHireDate(LocalDate.parse(hireDateInput.trim()));
            } catch (DateTimeParseException ex) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hire Date must be in yyyy-MM-dd format", null));
                return null;
            }
        }

        try {
            employeeService.create(employee);
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Could not create employee. Use a unique Employee ID and valid values.", null));
            return null;
        }

        FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Employee created successfully"));

        return "employees?faces-redirect=true";
    }

    public Employee getEmployee() {
        return employee;
    }

    public Integer getSelectedDeptId() {
        return selectedDeptId;
    }

    public void setSelectedDeptId(Integer selectedDeptId) {
        this.selectedDeptId = selectedDeptId;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public String getHireDateInput() {
        return hireDateInput;
    }

    public void setHireDateInput(String hireDateInput) {
        this.hireDateInput = hireDateInput;
    }
}
