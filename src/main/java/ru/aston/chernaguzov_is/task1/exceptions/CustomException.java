package ru.aston.chernaguzov_is.task1.exceptions;

public class CustomException extends Exception{
    private final String message;
    private final CodException status;

    public CustomException(String message, CodException status) {
        this.message = message;
        this.status = status;
    }

    public CodException getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "message='" + message + '\'' +
                ", status=" + status +
                '}';
    }
}
