package com.springboot.project.DocumentDetailsService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DocumentDetailsExceptionController {
    @ExceptionHandler(value = IDNotFoundException.class)
    public ResponseEntity<Object> exception(IDNotFoundException exception) {
        return new ResponseEntity<>("ID not found", HttpStatus.NOT_FOUND);
    }
}
