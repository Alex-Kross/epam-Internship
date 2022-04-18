package com.epam.jwd.task2.model.logic.exception.implEmptyException;

import com.epam.jwd.task2.model.logic.exception.ValueEmptyException;

public abstract class CollectionEmptyException extends ValueEmptyException {
    public CollectionEmptyException() {
        super();
    }

    public CollectionEmptyException(String message) {
        super(message);
    }

    public CollectionEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
