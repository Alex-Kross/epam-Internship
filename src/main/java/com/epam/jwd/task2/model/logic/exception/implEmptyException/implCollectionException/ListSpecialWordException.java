package com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException;

import com.epam.jwd.task2.model.logic.exception.implEmptyException.CollectionEmptyException;

public class ListSpecialWordException extends CollectionEmptyException {
    public ListSpecialWordException() {
        super();
    }

    public ListSpecialWordException(String message) {
        super(message);
    }

    public ListSpecialWordException(String message, Throwable cause) {
        super(message, cause);
    }
}
