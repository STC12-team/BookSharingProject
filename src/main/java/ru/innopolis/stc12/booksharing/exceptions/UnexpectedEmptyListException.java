package ru.innopolis.stc12.booksharing.exceptions;

public class UnexpectedEmptyListException extends RuntimeException {
    public UnexpectedEmptyListException(String message) {
        super(message);
    }
}
