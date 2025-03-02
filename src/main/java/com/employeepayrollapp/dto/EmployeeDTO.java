package com.employeepayrollapp.dto;

import com.employeepayrollapp.entity.EmployeeEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {

    // Name field with validation annotations
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Name should only contain alphabetic characters and spaces")
    private String name;

    private double salary;

    // Constructor to convert EmployeeEntity to EmployeeDTO
    public EmployeeDTO(EmployeeEntity employeeEntity) {
        this.name = employeeEntity.getName();
        this.salary = employeeEntity.getSalary();
    }
}
