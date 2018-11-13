package ru.innopolis.stc12.booksharing.exceptions;

public class ControllerException extends RuntimeException {
    private String exceptionMsg;

    public ControllerException(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }

    public String getExceptionMsg() {
        return this.exceptionMsg;
    }

    public void setExceptionMsg(String exceptionMsg) {
        this.exceptionMsg = exceptionMsg;
    }
}
