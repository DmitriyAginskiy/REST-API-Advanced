package com.epam.esm.exception;

import com.epam.esm.exception.util.MessageKey;

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

    public static final int ERROR_CODE = 40402;
    Object[] fieldValue;

    public OperationNotPerformedException(Object... fieldValue) {
        this.fieldValue = fieldValue;
    }

    @Override
    public String getLocalizedMessage() {
        return new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH.getMessageKey(), Locale.getDefault())
                .getString(MessageKey.OPERATION_NOT_PERFORMED.getMessageKey())
                .getBytes(StandardCharsets.ISO_8859_1)) + Arrays.toString(fieldValue);
    }
}
