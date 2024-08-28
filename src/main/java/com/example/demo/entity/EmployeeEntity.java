package com.example.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table (name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Employee name cannot be empty")
    @Size(max = 6, min = 3, message = "Maximum employee name is 6, minimum is 3")
    private String employeeName;

    @NotEmpty(message = "Employee department name cannot be empty")
    private String departmentName;

    @NotEmpty(message = "Employee Salary cannot be empty")
    private Long employeeSalary;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;



}
