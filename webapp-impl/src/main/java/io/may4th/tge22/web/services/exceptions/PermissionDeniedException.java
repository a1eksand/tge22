package io.may4th.tge22.web.services.exceptions;

public class PermissionDeniedException extends RuntimeException {

    public PermissionDeniedException() {
        super();
    }

    public PermissionDeniedException(String msg) {
        super(msg);
    }

    public PermissionDeniedException(String msg, Throwable t) {
        super(msg, t);
    }
}
