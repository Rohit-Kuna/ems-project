package com.rohit.ems.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmsErrorEnum {

    INTERNAL_SERVER_ERROR(30000000, Level.ERROR, "Internal Server Error",
                        Constants.INTERNAL_SER_ERR, Severity.MAJOR, "1.0", "no");

    private final Integer errorNumber;
    private final Level level;
    private final String message;
    private final String rootCause;
    private final Severity severity;
    private final String implementationVersion;
    private final String automation;

    private enum Severity {
        MINOR,
        MEDIUM,
        MAJOR;
        private Severity() {
        }
    }

    private enum Level {
        INFO,
        WARNING,
        CRITICAL,
        ERROR,
        DEBUG;
        private Level() {
        }
    }

    private static final class Constants {
                private static final String INTERNAL_SER_ERR = "Internal server error";
                private static final String REST_CLIENT_EXCEPTION = "REST client exception";
                private static final String BUSINESS_EXCEPTION = "Business exception";
    }
}
