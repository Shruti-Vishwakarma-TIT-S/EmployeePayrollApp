package com.employeepayrollapp.controller;

import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    // UC-1 -------------------------------- starts here
    @Autowired
    private EmployeeService employeeService;

    // To add new data
    @PostMapping
    public EmployeeEntity addEmployee(@RequestBody EmployeeEntity employee) {
        return employeeService.saveEmployee(employee);
    }

    // To retrieve data
    @GetMapping
    public List<EmployeeEntity> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // To retrieve data based on id
    @GetMapping("/{id}")
    public Optional<EmployeeEntity> getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    // To delete data based on id
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }

    // Update the changes in the file data
    @PutMapping("/{id}")
    public EmployeeEntity updateEmployee(@PathVariable Long id, @RequestBody EmployeeEntity newEmployee) {
        return employeeService.updateEmployee(id, newEmployee);
    }

    // UC2 -------------------------------- starts here
    // Get employees by department
    @GetMapping("/department/{department}")
    public List<EmployeeEntity> getEmployeesByDepartment(@PathVariable String department) {
        return employeeService.getEmployeesByDepartment(department);
    }

    // Update employee's salary
    @PatchMapping("/{id}/salary")
    public EmployeeEntity updateEmployeeSalary(@PathVariable Long id, @RequestBody double newSalary) {
        return employeeService.updateEmployeeSalary(id, newSalary);
    }
}
