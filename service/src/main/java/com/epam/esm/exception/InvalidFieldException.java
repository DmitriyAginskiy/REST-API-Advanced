package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageKey;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom InvalidField exception.
 *
 * @author Dzmitry Ahinski
 */
public class InvalidFieldException extends RuntimeException {

    public static final int ERROR_CODE = 40402;
    String objectField;

    public InvalidFieldException(long objectField) {
        this.objectField = String.valueOf(objectField);
    }

    public InvalidFieldException(String objectField) {
        this.objectField = objectField;
    }

    @Override
    public String getLocalizedMessage() {
        return new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH, Locale.getDefault())
                .getString(MessageKey.INVALID_FIELD_KEY)
                .getBytes(StandardCharsets.ISO_8859_1)) + objectField;
    }
}
