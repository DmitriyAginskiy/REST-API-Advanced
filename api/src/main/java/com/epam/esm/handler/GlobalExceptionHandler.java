package com.epam.esm.handler;

import com.epam.esm.handler.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Global exception handler.
 *
 * @author Dzmitry Ahinski
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions with 404 status.
     *
     * @return response entity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
