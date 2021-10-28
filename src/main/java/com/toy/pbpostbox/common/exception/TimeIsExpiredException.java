package com.toy.pbpostbox.common.exception;

public class TimeIsExpiredException extends RuntimeException {

    public TimeIsExpiredException(String errorMessage) {
        this(errorMessage, null);
    }

    public TimeIsExpiredException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
