package de.atruvia.webapp.service.exception;

public class BlacklistException extends RuntimeException{
    public BlacklistException() {
    }

    public BlacklistException(final String message) {
        super(message);
    }

    public BlacklistException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public BlacklistException(final Throwable cause) {
        super(cause);
    }

    public BlacklistException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
