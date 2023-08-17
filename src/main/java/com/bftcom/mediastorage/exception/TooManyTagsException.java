package com.bftcom.mediastorage.exception;

public class TooManyTagsException extends Exception {
    public TooManyTagsException() {
    }

    public TooManyTagsException(String message) {
        super(message);
    }

    public TooManyTagsException(String message, Throwable cause) {
        super(message, cause);
    }

    public TooManyTagsException(Throwable cause) {
        super(cause);
    }
}
