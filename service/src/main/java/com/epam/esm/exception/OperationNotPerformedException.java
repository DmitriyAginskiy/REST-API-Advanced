package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageManager;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom OperationNotPerformed exception.
 *
 * @author Dzmitry Ahinski
 */
public class OperationNotPerformedException extends RuntimeException {

    public OperationNotPerformedException(String message) {
        super(message);
    }
}
