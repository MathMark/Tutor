package com.gbsfo.tutor.springboottutor.controller;

import com.gbsfo.tutor.springboottutor.exception.EmptyResultException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.EntityNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(
        EntityNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NOT_FOUND.value());
        body.put("message", HttpStatus.NOT_FOUND.getReasonPhrase());

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(EmptyResultException.class)
    public ResponseEntity<Object> handleEmptyResultException(
        EmptyResultException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.NO_CONTENT.value());
        body.put("message", HttpStatus.NO_CONTENT.getReasonPhrase());

        return new ResponseEntity<>(body, HttpStatus.NO_CONTENT);
    }
}
