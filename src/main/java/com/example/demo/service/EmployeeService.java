package com.example.demo.service;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repository.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeEntity> getEmployeeList() {
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> findEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return employeeRepository.save(employee);
    }

    public EmployeeEntity updateEmployee(EmployeeEntity employeeEntity, Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);

        if(employee.isPresent()) {
            EmployeeEntity entity = employee.get();
            entity.setEmployeeName(employeeEntity.getEmployeeName());
            entity.setEmployeeSalary(employeeEntity.getEmployeeSalary());

            return employeeRepository.save(entity);
        } else {
            throw new EntityNotFoundException("Employee with ID " + employeeEntity.getId() + " not found");
        }

    }


    public String deleteAllEmployee() {
        employeeRepository.deleteAll();
        return "All employee deleted";
    }
}
