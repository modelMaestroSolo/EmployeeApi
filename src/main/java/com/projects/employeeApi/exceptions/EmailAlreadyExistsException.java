package com.projects.employeeApi.exceptions;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(){
    }

    public EmailAlreadyExistsException(String message){
        super(message);
    }
}
