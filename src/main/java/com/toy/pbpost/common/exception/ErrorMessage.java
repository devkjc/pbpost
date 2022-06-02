package com.toy.pbpost.common.exception;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessage {
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED"),
    ENTITY_NOT_FOUND("ENTITY_NOT_FOUND"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR"),
    INVALID_TYPE_VALUE("INVALID_TYPE_VALUE"),
    PROCESS_ERROR("PROCESS_ERROR"),
    HANDLE_ACCESS_DENIED("HANDLE_ACCESS_DENIED"),
    UNAUTHORIZED("UNAUTHORIZED"),
    DUPLICATE_KEY("DUPLICATE_KEY");

    private final String message;

    ErrorMessage(final String message) {
        this.message = message;
    }

    @JsonValue
    public String toValue() {
        return message;
    }
}
