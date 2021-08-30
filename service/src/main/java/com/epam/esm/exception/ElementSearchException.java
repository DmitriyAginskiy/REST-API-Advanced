package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageKey;

import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Custom ElementSearch exception.
 *
 * @author Dzmitry Ahinski
 */
public class ElementSearchException extends RuntimeException {

    public static final int ERROR_CODE = 40401;
    String objectField;

    public ElementSearchException(long objectField) {
        this.objectField = String.valueOf(objectField);
    }

    public ElementSearchException(String objectField) {
        this.objectField = objectField;
    }

    @Override
    public String getLocalizedMessage() {
        return new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH, Locale.getDefault())
                .getString(MessageKey.ELEMENT_SEARCH_KEY)
                .getBytes(StandardCharsets.ISO_8859_1)) + objectField;
    }
}
