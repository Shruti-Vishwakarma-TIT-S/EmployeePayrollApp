package com.employeepayrollapp.service;

import com.employeepayrollapp.entity.EmployeeEntity;
import com.employeepayrollapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    // Used for dependency injection

    @Autowired
    private EmployeeRepository repository;

    public EmployeeEntity saveEmployee(EmployeeEntity employee) {
        return repository.save(employee);
    }

    public List<EmployeeEntity> getAllEmployees() {
        return repository.findAll();
    }

    public Optional<EmployeeEntity> getEmployeeById(Long id) {
        return repository.findById(id);
    }

    public void deleteEmployee(Long id) {
        repository.deleteById(id);
    }

    public EmployeeEntity updateEmployee(Long id, EmployeeEntity newEmployee) {
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setDepartment(newEmployee.getDepartment());
                    employee.setSalary(newEmployee.getSalary());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    newEmployee.setId(id);
                    return repository.save(newEmployee);
                });

    }
}
