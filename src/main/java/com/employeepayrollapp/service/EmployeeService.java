package com.employeepayrollapp.service;

import com.employeepayrollapp.dto.EmployeeDTO;
import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// UC1 - Lombok Library---------------------- we are using lombok here
@Service
public class EmployeeService {

    public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeEntity saveEmployees(EmployeeDTO employee) {
        EmployeeEntity emp = new EmployeeEntity();
        emp.setName(employee.getName());
        emp.setSalary(employee.getSalary());
        logger.info("Saving employee: {}", employee.getName());

        return employeeRepository.save(emp);
    }

    public List<EmployeeEntity> getAllEmployees() {
        logger.info("Getting all employees");
        return employeeRepository.findAll();
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        logger.info("Get employee details by id: {}", id);
        return employeeRepository.findById(id);
    }

    public void deleteEmployee(Long id) {
        logger.info("Delete employee.");
        employeeRepository.deleteById(id);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeDTO newEmployee) {
        logger.info("Updating employee details.");
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalEmployee.get();
            existingEmployee.setName(newEmployee.getName());
            existingEmployee.setSalary(newEmployee.getSalary());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
}
