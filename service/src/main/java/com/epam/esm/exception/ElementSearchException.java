package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageManager;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom ElementSearch exception.
 *
 * @author Dzmitry Ahinski
 */
public class ElementSearchException extends RuntimeException {

    public ElementSearchException(String message) {
        super(message);
    }
}
