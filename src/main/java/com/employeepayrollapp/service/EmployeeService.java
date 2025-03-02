package com.employeepayrollapp.service;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.exception.EmployeeNotFoundException;
import com.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    // Save employee detail
    public EmployeeEntity saveEmployees(EmployeeDTO employee) {
        log.info("Saving employee: {}", employee.getName());
        EmployeeEntity emp = new EmployeeEntity();
        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        return employeeRepository.save(emp);
    }

    // Get employee details
    public List<EmployeeEntity> getAllEmployees() {
        log.info("Getting all employees");
        return employeeRepository.findAll();
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        log.info("Delete employee.");
        employeeRepository.deleteById(id);
    }

    // Update employee details
    public EmployeeEntity updateEmployee(Long id, EmployeeDTO newEmployee) {
        log.info("Updating employee details.");
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalEmployee.get();
            existingEmployee.setName(newEmployee.getName());
            existingEmployee.setSalary(newEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        }
        return null;  // If employee not found, return null
    }

    // Get employees by department
    public List<EmployeeEntity> getEmployeesByDepartment(String department) {
        log.info("Fetching employees for department: {}", department);
        return employeeRepository.findByDepartment(department);
    }

    // Get employees by salary range
    public List<EmployeeEntity> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        log.info("Fetching employees with salary between {} and {}", minSalary, maxSalary);
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    // Update employee department
    public EmployeeEntity updateEmployeeDepartment(Long id, String newDepartment) {
        log.info("Updating department for employee with id: {}", id);
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setDepartment(newDepartment);
            return employeeRepository.save(employee);
        }
        return null;
    }

    // Partially update employee salary
    public EmployeeEntity partialUpdateEmployee(Long id, double newSalary) {
        log.info("Updating salary for employee with id: {}", id);
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity employee = optionalEmployee.get();
            employee.setSalary(newSalary);
            return employeeRepository.save(employee);
        }
        return null;
    }

    // Get employee by ID (throws EmployeeNotFoundException if not found)
    public EmployeeEntity getEmployeeById(Long id) {
        Optional<EmployeeEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        } else {
            throw new EmployeeNotFoundException("Employee with ID " + id + " not found.");
        }
    }
}
