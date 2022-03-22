package io.may4th.tge22.security.api.exceptions;

public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String msg) {
        super(msg);
    }

    public AccessDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
}
