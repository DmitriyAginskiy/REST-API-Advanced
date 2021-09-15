package com.epam.esm.handler;

import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.exception.util.ServiceMessageManager;
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
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles all exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionResponse> handleException(Throwable e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(HttpStatus.BAD_REQUEST.value(), ServiceMessageManager.SOMETHING_WENT_WRONG.getMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
