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

    // UC1 - DTO ----------------------------- starts here

    public static final Logger logger= LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    public EmployeeService employeeService;
    @PostMapping
    public ResponseEntity<?> addEmployees(@RequestBody EmployeeDTO employee){
        logger.info("Adding a new employee: {}", employee.getName());
        EmployeeEntity saveEmployee= employeeService.saveEmployees(employee);
        return ResponseEntity.ok(new EmployeeDTO(saveEmployee));
    }
    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> getAllEmployees(){
        logger.info("Get all employee details");
        List<EmployeeEntity> getEmployees=  employeeService.getAllEmployees();
        List<EmployeeDTO> getEmployeeDetails= new ArrayList<>();
        for(EmployeeEntity entity: getEmployees){
            getEmployeeDetails.add(new EmployeeDTO(entity));
        }
        return ResponseEntity.ok(getEmployees);
    }
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id){
        logger.info("Get employee details by id: {}", id);
        Optional<EmployeeEntity> getEmployee = employeeService.getEmployeeById(id);
        if(getEmployee.isPresent()){
            return ResponseEntity.ok(new EmployeeDTO(getEmployee.get()));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<EmployeeDTO> deleteEmployee(@PathVariable Long id){
        logger.info("Delete employee.");
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO){
        logger.info("Updated employee details.");
        EmployeeEntity updateEmployee= employeeService.updateEmployee(id, employeeDTO);
        return ResponseEntity.ok(new EmployeeDTO(updateEmployee));
    }
}
