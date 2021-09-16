package com.epam.esm.util;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Manages exceptions of web layer.
 *
 * @author Dzmitry Ahinski
 */
public enum ExceptionManager {

    INVALID_PARAMETER(40002, "invalid_parameter"),
    INVALID_FIELD(40003, "invalid_field"),
    UNSUPPORTED_MEDIA_TYPE(41501, "unsupported_media_type"),
    METHOD_NOT_ALLOWED(40501, "method_not_allowed");

    private static final String BUNDLE_PATH = "global_message";

    private int code;
    private String message;

    ExceptionManager(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
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
