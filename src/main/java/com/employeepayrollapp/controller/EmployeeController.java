package com.employeepayrollapp.controller;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    // Add new Data
    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody EmployeeDTO employee) {
        logger.info("Adding a new employee: {}", employee.getName());
        EmployeeEntity savedEmployee = employeeService.saveEmployees(employee);
        return ResponseEntity.ok(new EmployeeDTO(savedEmployee)); // Use the constructor here
    }

    // For displaying all details
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        logger.info("Get all employee details");
        List<EmployeeEntity> employees = employeeService.getAllEmployees();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (EmployeeEntity entity : employees) {
            employeeDTOs.add(new EmployeeDTO(entity)); // Convert EmployeeEntity to EmployeeDTO
        }
        return ResponseEntity.ok(employeeDTOs);
    }

    // get mapping by id
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        logger.info("Get employee details by id: {}", id);
        Optional<EmployeeEntity> employee = employeeService.getEmployeeById(id);
        if (employee.isPresent()) {
            return ResponseEntity.ok(new EmployeeDTO(employee.get())); // Use the constructor here
        }
        return ResponseEntity.notFound().build();
    }

    // Delete Mapping to delete body
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("Delete employee.");
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    // Put mapping to update details
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        logger.info("Update employee details.");
        EmployeeEntity updatedEmployee = employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(new EmployeeDTO(updatedEmployee)); // Use the constructor here
    }
}
