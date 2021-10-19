package com.toy.pbpostbox.common.exception;

import org.springframework.http.HttpStatus;

public class ProcessException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProcessException() {
        super();
    }

    public ProcessException(Throwable e) {
        super(e);
    }

    public ProcessException(String errorMessge) {
        super(errorMessge);
    }

    public ProcessException(String errorMessge, Throwable e) {
        super(errorMessge, e);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.GONE;
    }
}
