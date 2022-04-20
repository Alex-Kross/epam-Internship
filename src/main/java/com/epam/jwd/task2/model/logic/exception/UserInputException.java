package com.epam.jwd.task2.model.logic.exception;

public class UserInputException extends RuntimeException{
    public UserInputException() {
        super();
    }

    public UserInputException(String message) {
        super(message);
    }

    public UserInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
