package com.employeepayrollapp.repository;

import com.employeepayrollapp.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Interface EmployeeRepository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByDepartment(String department);  // Find employees by department
    List<EmployeeEntity> findBySalaryBetween(double minSalary, double maxSalary);  // Find employees by salary range
}
