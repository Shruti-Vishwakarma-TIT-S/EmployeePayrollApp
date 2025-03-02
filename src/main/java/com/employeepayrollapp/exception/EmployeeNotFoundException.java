package com.employeepayrollapp.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(String message) {
        // Call the parent constructor with the error message
        super(message);
    }
}
