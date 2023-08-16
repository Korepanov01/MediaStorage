package com.bftcom.mediastorage.exception;

public class TooManyFilesException extends Exception {
    public TooManyFilesException() {
    }

    public TooManyFilesException(String message) {
        super(message);
    }

    public TooManyFilesException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyFilesException(Throwable cause) {
        super(cause);
    }
}
