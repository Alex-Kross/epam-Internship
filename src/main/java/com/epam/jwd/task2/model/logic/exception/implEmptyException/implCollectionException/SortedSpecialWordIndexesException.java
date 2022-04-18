package com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException;

import com.epam.jwd.task2.model.logic.exception.implEmptyException.CollectionEmptyException;

public class SortedSpecialWordIndexesException extends CollectionEmptyException {
    public SortedSpecialWordIndexesException() {
        super();
    }

    public SortedSpecialWordIndexesException(String message) {
        super(message);
    }

    public SortedSpecialWordIndexesException(String message, Throwable cause) {
        super(message, cause);
    }
}
