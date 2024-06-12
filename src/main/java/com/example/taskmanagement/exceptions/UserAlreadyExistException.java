package com.example.taskmanagement.exceptions;

public class UserAlreadyExistException extends RuntimeException {
    public UserAlreadyExistException(String thisAccountIsAlreadyExist) {
        super(thisAccountIsAlreadyExist);
    }
}
