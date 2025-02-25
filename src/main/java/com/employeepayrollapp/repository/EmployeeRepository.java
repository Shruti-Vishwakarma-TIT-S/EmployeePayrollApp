package com.employeepayrollapp.repository;


import com.employeepayrollapp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // UC2--------------------------------- starts here

        // Find employees by department
        List<EmployeeEntity> findByDepartment(String department);

        // Custom query to find employees by salary greater than a certain amount
        @Query("SELECT e FROM EmployeeEntity e WHERE e.salary > ?1")
        List<EmployeeEntity> findBySalaryGreaterThan(double salary);
}
