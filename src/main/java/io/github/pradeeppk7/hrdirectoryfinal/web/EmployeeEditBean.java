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
import java.util.List;

@Named
@ViewScoped
public class EmployeeEditBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private EmployeeService employeeService;

    @EJB
    private DepartmentService departmentService;

    private Employee employee;
    private Integer empId;
    private boolean employeeFound;
    private Integer selectedDeptId;
    private List<Department> departments;

    @PostConstruct
    public void init() {
        employee = new Employee();
        employeeFound = false;
        departments = departmentService.getAllDepartments();

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
                    if (loaded.getDepartment() != null) {
                        selectedDeptId = loaded.getDepartment().getDeptId();
                    }
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

        Department selectedDepartment = departmentService.findById(selectedDeptId);
        if (selectedDepartment == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a valid department", null));
            return null;
        }
        employee.setDepartment(selectedDepartment);

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

    public Integer getSelectedDeptId() {
        return selectedDeptId;
    }

    public void setSelectedDeptId(Integer selectedDeptId) {
        this.selectedDeptId = selectedDeptId;
    }

    public List<Department> getDepartments() {
        return departments;
    }
}
