package com.vinsguru.controller;

import com.vinsguru.dto.StudentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StudentException> handleGenericException(Exception e){
        StudentException studentException = new StudentException(100, "Students are not found");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(studentException);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StudentException> handleRunTimeException(RuntimeException e, HttpServletRequest request){
        final Map<String, String> pathVariables = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        StudentException studentException = new StudentException(101, String.format("Student with %s is not found", pathVariables.get("id")));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(studentException);
    }

}
