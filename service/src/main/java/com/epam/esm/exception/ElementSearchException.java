package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageKey;

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

    public static final int ERROR_CODE = 40401;
    Object[] fieldValue;

    public ElementSearchException(Object... fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String getLocalizedMessage() {
        return new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH.getMessageKey(), Locale.getDefault())
                .getString(MessageKey.ELEMENT_SEARCH_KEY.getMessageKey())
                .getBytes(StandardCharsets.ISO_8859_1)) + Arrays.toString(fieldValue);
    }
}
