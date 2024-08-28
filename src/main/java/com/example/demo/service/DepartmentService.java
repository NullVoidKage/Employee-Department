package com.example.demo.service;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.repository.DepartmentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public List<DepartmentEntity> getAllDepartment() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }


    public DepartmentEntity saveDepartment(DepartmentEntity departmentEntity) {
        return departmentRepository.save(departmentEntity);
    }

    public DepartmentEntity updateDepartmentById(Long id, DepartmentEntity departmentEntity) {
        // Retrieve the existing department from the database using its ID
        Optional<DepartmentEntity> departmentOptional = departmentRepository.findById(id);

        if (departmentOptional.isPresent()) {
            DepartmentEntity existingDepartment = departmentOptional.get();

            // Update the department's fields (name, etc.)
            existingDepartment.setName(departmentEntity.getName());

            // Update the employees associated with this department
            Set<EmployeeEntity> updatedEmployees = departmentEntity.getEmployees();

            // Set the department for each employee
            for (EmployeeEntity employee : updatedEmployees) {
                employee.setDepartment(existingDepartment);
            }

            // Replace the old employee set with the new one
            existingDepartment.setEmployees(updatedEmployees);

            // Save the updated department entity back to the database
            return departmentRepository.save(existingDepartment);
        } else {
            throw new EntityNotFoundException("Department with ID " + departmentEntity.getId() + " not found");
        }
    }


    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }

    public String deleteAllDepartment() {
        departmentRepository.deleteAll();

        return "All department Deleted";
    }




}
