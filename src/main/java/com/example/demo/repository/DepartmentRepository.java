package com.example.demo.repository;

import com.example.demo.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {

    @Query(value = "SELECT name FROM Employee", nativeQuery = true)
    String getString();
}
