package com.example.taskmanagement.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
public class ErrorDetails {
    private String message;
    private int statusCode;
}
