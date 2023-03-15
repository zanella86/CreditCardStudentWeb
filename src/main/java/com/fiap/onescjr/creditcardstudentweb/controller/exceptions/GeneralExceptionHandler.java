package com.fiap.onescjr.creditcardstudentweb.controller.exceptions;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler( { NoSuchElementException.class, EntityNotFoundException.class })
    public ResponseEntity<Object> handleException(Exception ex){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleException(MissingServletRequestParameterException ex) {
        return ResponseEntity.unprocessableEntity().body(ex.getMessage());
    }
}
