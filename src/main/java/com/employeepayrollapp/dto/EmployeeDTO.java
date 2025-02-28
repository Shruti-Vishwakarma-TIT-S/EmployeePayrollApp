package com.employeepayrollapp.dto;

import com.employeepayrollapp.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    // UC1- Lombok Library , we are using this library so that it will make its own getter and setter.

    private String name;
    private double salary;

    // Constructor to convert EmployeeEntity to EmployeeDTO
    public EmployeeDTO(EmployeeEntity employeeEntity) {
        this.name = employeeEntity.getName();
        this.salary = employeeEntity.getSalary();
    }
}
