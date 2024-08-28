package com.example.demo.controller;

import com.example.demo.entity.EmployeeEntity;
import com.example.demo.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getEmployees() {
        return ResponseEntity.ok(employeeService.getEmployeeList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeEntity> getEmployeeById(@PathVariable("id") Long id) {
        Optional<EmployeeEntity> employee = employeeService.findEmployeeById(id);
        return employee.map(ResponseEntity::ok).orElseGet(
                () -> ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<EmployeeEntity> addEmployee(@Valid @RequestBody EmployeeEntity employee) {
        return new ResponseEntity<>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<EmployeeEntity> updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeEntity employee) {
        if (employeeService.findEmployeeById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        employee.setId(id);
        return new ResponseEntity<>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllEmployee() {
        return new ResponseEntity<>(employeeService.deleteAllEmployee(), HttpStatus.OK);
    }

}
