package com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException;

import com.epam.jwd.task2.model.logic.exception.implEmptyException.CollectionEmptyException;

public class NumberSpecialWordException extends CollectionEmptyException {
    public NumberSpecialWordException() {
        super();
    }

    public NumberSpecialWordException(String message) {
        super(message);
    }

    public NumberSpecialWordException(String message, Throwable cause) {
        super(message, cause);
    }
}
