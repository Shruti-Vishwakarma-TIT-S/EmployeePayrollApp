package com.employeepayrollapp.controller;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
// Lombok will automatically generate a 'log' object
@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Add new Data
    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody EmployeeDTO employee) {
        log.info("Adding a new employee: {}", employee.getName());
        EmployeeEntity savedEmployee = employeeService.saveEmployees(employee);
        return ResponseEntity.ok(new EmployeeDTO(savedEmployee));
    }

    // For displaying all details
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

    // get mapping by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        log.info("Get employee details by id: {}", id);
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(new EmployeeDTO(employee.get()));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete Mapping to delete body
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        log.info("Delete employee.");
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Put mapping to update details
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        log.info("Update employee details.");
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));
    }

    // UC2 - LombokLibraryLogging-------------------------------------

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

    // Get Mapping using salary
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
            employeeDTOs.add(new EmployeeDTO(entity));  // Convert EmployeeEntity to EmployeeDTO
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // Put mapping using department
    @PutMapping("/{id}/department")
    public ResponseEntity<EmployeeDTO> updateEmployeeDepartment(@PathVariable Long id, @RequestParam String newDepartment) {
        log.info("Received request to update department for employee with id: {} to {}", id, newDepartment);
        EmployeeEntity updatedEmployee = employeeService.updateEmployeeDepartment(id, newDepartment);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));  // Convert to DTO and return
        }
        log.error("Failed to update department for employee with id: {}", id);
        return ResponseEntity.notFound().build();  // Return 404 if employee not found
    }

    // Post mapping by id salary
    @PatchMapping("/{id}/salary")
    public ResponseEntity<EmployeeDTO> partialUpdateEmployeeSalary(@PathVariable Long id, @RequestParam double newSalary) {
        log.info("Received request to update salary for employee with id: {} to {}", id, newSalary);
        EmployeeEntity updatedEmployee = employeeService.partialUpdateEmployee(id, newSalary);
        if (updatedEmployee != null) {
            return ResponseEntity.ok(new EmployeeDTO(updatedEmployee));  // Convert to DTO and return
        }
        log.error("Failed to update salary for employee with id: {}", id);
        return ResponseEntity.notFound().build();  // Return 404 if employee not found
    }
}
