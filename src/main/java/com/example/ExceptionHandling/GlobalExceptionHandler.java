package com.example.ExceptionHandling;


import org.springframework.web.bind.annotation.*;

import com.example.exception.ResourceNotFoundException;

import org.springframework.http.*;
import  org.springframework.web.bind.*;

@ControllerAdvice
public class GlobalExceptionHandler {


    // To Handle Validation Exception
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Exception> handleValidationException(MethodArgumentNotValidException exception) {
    
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception);
    
    }
    
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex, HttpStatus.NOT_FOUND);
    }
}