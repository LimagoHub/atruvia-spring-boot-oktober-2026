package de.atruvia.webapp.service.exception;

public class AlreadyExistsException extends RuntimeException{
    public AlreadyExistsException(final String message) {
        super(message);
    }
}
