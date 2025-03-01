package com.employeepayrollapp.service;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
// Lombok automatically creates the 'log' object
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

    // Get employee by id
    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        log.info("Get employee details by id: {}", id);
        return employeeRepository.findById(id);
    }

    // Delete employee
    public void deleteEmployee(Long id) {
        log.info("Delete employee.");
        employeeRepository.deleteById(id);
    }

    // Update by employee
    public EmployeeEntity updateEmployee(Long id, EmployeeDTO newEmployee) {
        log.info("Updating employee details.");
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalEmployee.get();
            existingEmployee.setName(newEmployee.getName());
            existingEmployee.setSalary(newEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }

    // UC2 - LombokLibraryLogging---------------------------------

    // Get employee details by department
    public List<EmployeeEntity> getEmployeesByDepartment(String department) {
        log.info("Fetching employees for department: {}", department);
        return employeeRepository.findByDepartment(department);
    }

    // Get employee's information by salary range
    public List<EmployeeEntity> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        log.info("Fetching employees with salary between {} and {}", minSalary, maxSalary);
        return employeeRepository.findBySalaryBetween(minSalary, maxSalary);
    }

    // To update employee's information by department
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

    // To update employee's information partially
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
}
