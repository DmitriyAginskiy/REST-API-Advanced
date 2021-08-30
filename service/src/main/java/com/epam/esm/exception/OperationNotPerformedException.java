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
public class OperationNotPerformedException extends RuntimeException {

    public static final int ERROR_CODE = 40403;
    long objectId;

    public OperationNotPerformedException(long objectId) {
        this.objectId = objectId;
    }

    @Override
    public String getLocalizedMessage() {
        return new String(ResourceBundle
                .getBundle(MessageKey.BUNDLE_PATH, Locale.getDefault())
                .getString(MessageKey.OPERATION_NOT_PERFORMED)
                .getBytes(StandardCharsets.ISO_8859_1)) + objectId;
    }
}
