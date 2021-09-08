package com.epam.esm.dao.exception.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {

    CAN_NOT_DELETE("can_not_delete"),
    CAN_NOT_PERSIST("can_not_persist");

    private static final String BUNDLE_PATH = "message";

    private String message;

    MessageManager(String message) {
        this.message = message;
    }

    public String getMessage(Object... objectFields) {
        return new String(ResourceBundle
                .getBundle(BUNDLE_PATH, Locale.getDefault())
                .getString(message)
                .getBytes(StandardCharsets.ISO_8859_1)) + Arrays.toString(objectFields);
    }
}
