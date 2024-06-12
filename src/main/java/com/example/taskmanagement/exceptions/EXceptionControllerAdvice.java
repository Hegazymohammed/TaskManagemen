package com.example.taskmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EXceptionControllerAdvice {
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<ErrorDetails>userExists(UserAlreadyExistException ex){
            HttpStatus status=HttpStatus.BAD_REQUEST;
            ErrorDetails errorDetails=new ErrorDetails(ex.getMessage(),status .value());
            return ResponseEntity.status(status).body(errorDetails);
    }
    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<ErrorDetails>TasksExists(TaskNotFoundException exception){
        HttpStatus status=HttpStatus.BAD_REQUEST;
        ErrorDetails errorDetails=new ErrorDetails(exception.getMessage(),status .value());
        return ResponseEntity.status(status).body(errorDetails);
    }
}
