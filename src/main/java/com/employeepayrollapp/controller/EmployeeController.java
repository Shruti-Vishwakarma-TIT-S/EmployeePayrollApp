package com.employeepayrollapp.controller;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add new employee with validation
    @PostMapping
    public ResponseEntity<?> addEmployees(@Valid @RequestBody EmployeeDTO employee, BindingResult bindingResult) {
        log.info("Adding a new employee: {}", employee.getName());

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        EmployeeEntity savedEmployee = employeeService.saveEmployees(employee);
        return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
    }

    // For displaying all employees
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        log.info("Get all employee details");
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeEntity entity : employees) {
            employeeDTOs.add(new EmployeeDTO(entity));
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // Delete employee by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("Delete employee.");
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Update employee details with validation
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO employeeDTO, BindingResult bindingResult) {
        log.info("Update employee details.");

        // Check for validation errors
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        }

        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));
        }
        return ResponseEntity.notFound().build();
    }

    // Get employees by department
    @GetMapping("/department/{department}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@PathVariable String department) {
        log.info("Received request to get employees by department: {}", department);
        List<EmployeeEntity> employees = employeeService.getEmployeesByDepartment(department);
        if (employees.isEmpty()) {
            log.warn("No employees found in department: {}", department);
            return ResponseEntity.noContent().build();
        }
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeEntity entity : employees) {
            employeeDTOs.add(new EmployeeDTO(entity));
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // Get employees by salary range
    @GetMapping("/salary-range")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesBySalaryRange(@RequestParam double minSalary, @RequestParam double maxSalary) {
        log.info("Received request to get employees with salary between {} and {}", minSalary, maxSalary);
        List<EmployeeEntity> employees = employeeService.getEmployeesBySalaryRange(minSalary, maxSalary);
        if (employees.isEmpty()) {
            log.warn("No employees found in the salary range {} to {}", minSalary, maxSalary);
            return ResponseEntity.noContent().build();
        }
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeEntity entity : employees) {
            employeeDTOs.add(new EmployeeDTO(entity));
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // Update employee department
    @PutMapping("/{id}/department")
    public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(@PathVariable Long id, @RequestParam String newDepartment) {
        log.info("Received request to update department for employee with id: {} to {}", id, newDepartment);
        EmployeeEntity updatedEmployee = employeeService.updateEmployeeDepartment(id, newDepartment);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));
        }
        log.error("Failed to update department for employee with id: {}", id);
        return ResponseEntity.notFound().build();
    }

    // Partial update employee salary
    @PatchMapping("/{id}/salary")
    public ResponseEntity<EmployeeDTO> partialUpdateEmployeeSalary(@PathVariable Long id, @RequestParam double newSalary) {
        log.info("Received request to update salary for employee with id: {} to {}", id, newSalary);
        EmployeeEntity updatedEmployee = employeeService.partialUpdateEmployee(id, newSalary);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));
        }
        log.error("Failed to update salary for employee with id: {}", id);
        return ResponseEntity.notFound().build();
    }

    // UC3- Throw user friendly error-----------------
    // Get employee by ID with validation
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        EmployeeEntity employee = employeeService.getEmployeeById(id);
        return ResponseEntity.ok(new EmployeeDTO(employee));
    }
}
