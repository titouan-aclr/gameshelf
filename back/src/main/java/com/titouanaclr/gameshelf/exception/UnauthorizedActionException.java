package com.titouanaclr.gameshelf.exception;

public class UnauthorizedActionException extends RuntimeException {
    public UnauthorizedActionException(String msg) {
        super(msg);
    }
}
