package com.example.demo.controller;

import com.example.demo.entity.DepartmentEntity;
import com.example.demo.service.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/v1/api/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentEntity>> getDepartments() {
        return new ResponseEntity<>(departmentService.getAllDepartment(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable("id") Long id) {
        Optional<DepartmentEntity> entity = departmentService.findDepartmentById(id);

        return entity.map(ResponseEntity::ok).orElseGet(() ->
                ResponseEntity.notFound().build()
        );
    }

    @PostMapping
    public ResponseEntity<DepartmentEntity> addDepartment(@Valid @RequestBody DepartmentEntity department) {
        return new ResponseEntity<>(departmentService.saveDepartment(department), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentEntity> updateDepartmentById(@PathVariable("id") Long id,@Valid @RequestBody DepartmentEntity department) {
        department.setId(id);
        return new ResponseEntity<>(departmentService.updateDepartmentById(id, department), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllDepartment() {
        return new ResponseEntity<>(departmentService.deleteAllDepartment(), HttpStatus.OK);
    }
}
