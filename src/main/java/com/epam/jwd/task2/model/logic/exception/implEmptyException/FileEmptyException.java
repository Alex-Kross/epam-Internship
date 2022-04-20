package com.epam.jwd.task2.model.logic.exception.implEmptyException;

import com.epam.jwd.task2.model.logic.exception.ValueEmptyException;

public class FileEmptyException extends ValueEmptyException {
    public FileEmptyException() {
    }

    public FileEmptyException(String message) {
        super(message);
    }

    public FileEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
