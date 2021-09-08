package com.epam.esm.exception.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

public enum MessageManager {

    ELEMENT_SEARCH_KEY("element_search"),
    OPERATION_NOT_PERFORMED("operation_not_performed"),
    SOMETHING_WENT_WRONG("something_went_wrong");

    private static final String BUNDLE_PATH = "message";

    private String message;

    MessageManager(String message) {
        this.message = message;
    }

    public String getMessage(Object... objectFields) {
        String localMessage = new String(ResourceBundle
                .getBundle(BUNDLE_PATH, Locale.getDefault())
                .getString(message)
                .getBytes(StandardCharsets.ISO_8859_1));
        if(objectFields.length != 0) {
            localMessage += Arrays.toString(objectFields);
        }
        return localMessage;
    }
}
