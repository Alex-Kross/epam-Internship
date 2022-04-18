package com.epam.jwd.task2.model.logic.exception.implEmptyException.implCollectionException;

import com.epam.jwd.task2.model.logic.exception.implEmptyException.CollectionEmptyException;

public class ListSentenceException extends CollectionEmptyException {
    public ListSentenceException() {
        super();
    }

    public ListSentenceException(String message) {
        super(message);
    }

    public ListSentenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
