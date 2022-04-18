package com.epam.jwd.task2.model.logic.exception.implFileException;

import com.epam.jwd.task2.model.logic.exception.FileException;

public class FileErrorException extends FileException {
    public FileErrorException() {
    }

    public FileErrorException(String message) {
        super(message);
    }

    public FileErrorException(String message, Throwable cause) {
        super(message, cause);
    }
}
