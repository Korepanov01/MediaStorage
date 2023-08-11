package com.bftcom.mediastorage.exception;

public class NameAlreadyUsedException extends Exception {
    public NameAlreadyUsedException() {
        super();
    }

    public NameAlreadyUsedException(String message) {
        super(message);
    }

    public NameAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public NameAlreadyUsedException(Throwable cause) {
        super(cause);
    }
}
