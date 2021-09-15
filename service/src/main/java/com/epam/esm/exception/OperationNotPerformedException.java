package com.epam.esm.exception;

/**
 * Custom OperationNotPerformed exception.
 *
 * @author Dzmitry Ahinski
 */
public class OperationNotPerformedException extends RuntimeException {

    public static final int ERROR_CODE = 40001;

    public OperationNotPerformedException(String message) {
        super(message);
    }
}
