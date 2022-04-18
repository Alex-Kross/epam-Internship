package com.epam.jwd.task2.model.logic.exception;

import java.io.IOException;

public abstract class FileException extends IOException {
    public FileException() {
    }

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
}
