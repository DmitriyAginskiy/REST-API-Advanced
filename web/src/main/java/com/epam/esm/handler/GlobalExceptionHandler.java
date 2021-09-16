package com.epam.esm.handler;

import com.epam.esm.exception.ElementSearchException;
import com.epam.esm.exception.OperationNotPerformedException;
import com.epam.esm.handler.entity.ExceptionResponse;
import com.epam.esm.util.ExceptionManager;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public ResponseEntity<ExceptionResponse> handleElementSearchException(ElementSearchException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ElementSearchException.ERROR_CODE, e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Operation Failed exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(OperationNotPerformedException.class)
    public ResponseEntity<ExceptionResponse> handleOperationNotPerformedException(OperationNotPerformedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(OperationNotPerformedException.ERROR_CODE, e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles MethodArgumentTypeMismatchException exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionManager.INVALID_PARAMETER.getCode(),
                ExceptionManager.INVALID_PARAMETER.getMessage(e.getValue()));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidFormatException exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidFormatException(InvalidFormatException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionManager.INVALID_FIELD.getCode(),
                ExceptionManager.INVALID_FIELD.getMessage(e.getValue()));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles HttpMediaTypeNotSupportedException exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionManager.UNSUPPORTED_MEDIA_TYPE.getCode(),
                ExceptionManager.UNSUPPORTED_MEDIA_TYPE.getMessage(e.getContentType()));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handles MethodNotAllowed exceptions.
     *
     * @return response entity
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ExceptionResponse> handleMethodNotAllowed(HttpRequestMethodNotSupportedException e) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(ExceptionManager.METHOD_NOT_ALLOWED.getCode(),
                ExceptionManager.METHOD_NOT_ALLOWED.getMessage(e.getMethod()));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
