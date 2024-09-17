package com.projects.employeeApi.controllers;

import com.projects.employeeApi.exceptions.EmailAlreadyExistsException;
import com.projects.employeeApi.exceptions.EmployeeNotFoundException;
import com.projects.employeeApi.exceptions.ErrorResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = DataAccessException.class)
    public ResponseEntity<ErrorResponse> handleDataAccessException(DataAccessException e){
        return ResponseEntity.internalServerError()
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(EmailAlreadyExistsException e){
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.CONFLICT.value(), e.getMessage() ),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = EmployeeNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException e){
        return new ResponseEntity<>(
                new ErrorResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }


}
