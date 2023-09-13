package ru.aston.chernaguzov_is.task4.exception;

import ru.aston.chernaguzov_is.task1.exceptions.CodException;

public class MyException extends RuntimeException{
    private final String message;
    private final CodException status;

    public MyException(String message, CodException status) {
        this.message = message;
        this.status = status;
    }

    public CodException getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
