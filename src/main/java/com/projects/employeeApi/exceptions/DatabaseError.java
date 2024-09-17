package com.projects.employeeApi.exceptions;

public class DatabaseError extends RuntimeException{
    private String message;

    public DatabaseError(String message){
        super(message);
    }
}
