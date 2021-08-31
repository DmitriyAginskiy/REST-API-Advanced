package com.epam.esm.handler;

import com.epam.esm.exception.util.MessageKey;
import com.epam.esm.handler.entity.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Global exception handler.
 *
 * @author Dzmitry Ahinski
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles all exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable e) {
        String message = new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH, Locale.getDefault())
                .getString(MessageKey.SOMETHING_WENT_WRONG)
                .getBytes(StandardCharsets.ISO_8859_1));
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
