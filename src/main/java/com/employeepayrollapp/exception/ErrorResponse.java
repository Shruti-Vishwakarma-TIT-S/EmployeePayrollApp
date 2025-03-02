package com.employeepayrollapp.exception;

import java.util.List;

public class ErrorResponse {
    private int status;
    private String message;
    private List<String> errors;

    // Constructor to handle errors (this one requires all 3 parameters)
    public ErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    // Constructor when no errors are present (this one requires only status and message)
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = null;  // Set errors to null if there are no errors
    }

    // Getters and setters
    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
