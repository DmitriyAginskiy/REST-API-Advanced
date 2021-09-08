package com.epam.esm.handler;

import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.exception.util.MessageManager;
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
     * Handles Element Search exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(ElementSearchException.class)
    public ResponseEntity<ExceptionResponse> handleException(ElementSearchException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ElementSearchException.ERROR_CODE, e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Operation Failed exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(OperationNotPerformedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPerformedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(OperationNotPerformedException.ERROR_CODE, e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles all exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable e) {
        String message = new String(ResourceBundle
                .getBundle(MessageManager.BUNDLE_PATH.getMessageKey(), Locale.getDefault())
                .getString(MessageManager.SOMETHING_WENT_WRONG.getMessageKey())
                .getBytes(StandardCharsets.ISO_8859_1));
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.NOT_FOUND.value(), message);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
