package com.epam.esm.exception.util;

public enum MessageKey {

    BUNDLE_PATH("message"),
    ELEMENT_SEARCH_KEY("element_search"),
    OPERATION_NOT_PERFORMED("operation_not_performed"),
    SOMETHING_WENT_WRONG("something_went_wrong");

    private String messageKey;

    MessageKey(String messageKey) {
        this.messageKey = messageKey;
    }

    public String getMessageKey() {
        return this.messageKey;
    }
}
