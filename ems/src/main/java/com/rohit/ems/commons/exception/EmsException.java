package com.rohit.ems.commons.exception;

import java.text.MessageFormat;

import org.springframework.http.HttpStatus;

public class EmsException extends RuntimeException{
    private final EmsErrorEnum errorEnum;
    private final HttpStatus status; 
    private final boolean logStackTrace;

    // convenience constructor
    public EmsException(EmsErrorEnum errorEnum, Object... messageArgs) {
        this(true, errorEnum, HttpStatus.INTERNAL_SERVER_ERROR, messageArgs);
    }

    public EmsException(boolean logStackTrace, EmsErrorEnum errorEnum, HttpStatus status, Object... messageArgs) {
        super(getMessage(errorEnum, messageArgs));
        this.errorEnum = errorEnum;
        this.logStackTrace = logStackTrace;
        this.status = status;
    }

    private static String getMessage(EmsErrorEnum errorEnum, Object... messageArgs) {
        String msg = errorEnum != null ? errorEnum.getMessage() : "ERROR_MESSAGE";
        if (messageArgs != null && messageArgs.length > 0) {
            msg = MessageFormat.format(msg, messageArgs);
        }
        return msg;
    }

    public String getErrorNumber() {
        return errorEnum.getErrorNumber().toString();
    }

    public boolean shouldLogStacktrace() {
        return logStackTrace;
    }
    
}
