package io.github.pradeeppk7.hrdirectoryfinal.entity;


import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Department")
public class Department implements Serializable {

    @Id
    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "dept_name", nullable = false, unique = true)
    private String deptName;

    public Department() {
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}