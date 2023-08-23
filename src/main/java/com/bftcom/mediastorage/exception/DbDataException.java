package com.bftcom.mediastorage.exception;

public class DbDataException extends RuntimeException {

    public DbDataException() {
    }

    public DbDataException(String message) {
        super(message);
    }

    public DbDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public DbDataException(Throwable cause) {
        super(cause);
    }
}
