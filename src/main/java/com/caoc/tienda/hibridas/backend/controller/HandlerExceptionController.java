package com.caoc.tienda.hibridas.backend.controller;

import com.caoc.tienda.hibridas.backend.exception.EmailUseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(EmailUseException.class)
    public ResponseEntity<ProblemDetail> handleEmailUseException(EmailUseException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        problemDetail.setTitle("Email Already In Use");
        problemDetail.setProperty("path", request.getRequestURI());
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }
}
