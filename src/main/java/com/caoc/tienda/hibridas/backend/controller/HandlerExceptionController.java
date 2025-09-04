package com.caoc.tienda.hibridas.backend.controller;

import com.caoc.tienda.hibridas.backend.exception.EmailNotFoundException;
import com.caoc.tienda.hibridas.backend.exception.EmailUseException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
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

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleEmailNotFoundException(EmailNotFoundException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setTitle("Email Not Found");
        problemDetail.setProperty("path", request.getRequestURI());
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ProblemDetail> handleAuthenticationException(AuthenticationException ex,
                                                                       HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Credenciales inválidas.");
        problemDetail.setTitle("Unauthorized");
        problemDetail.setProperty("path", request.getRequestURI());
        problemDetail.setProperty("timestamp", OffsetDateTime.now());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(problemDetail);
    }

}
