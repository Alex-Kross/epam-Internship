package com.epam.jwd.task2.model.logic.exception;

public abstract class ValueEmptyException extends RuntimeException{
    public ValueEmptyException() {
        super();
    }

    public ValueEmptyException(String message) {
        super(message);
    }

    public ValueEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
