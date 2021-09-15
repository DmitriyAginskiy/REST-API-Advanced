package com.epam.esm.exception;

/**
 * Custom ElementSearch exception.
 *
 * @author Dzmitry Ahinski
 */
public class ElementSearchException extends RuntimeException {

    public static final int ERROR_CODE = 40401;

    public ElementSearchException(String message) {
        super(message);
    }
}
