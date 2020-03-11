package com.idealista.infrastructure.exceptions;

public class ScoringIncompleteException extends Exception {
    public ScoringIncompleteException(String errorMessage, Throwable cause) {
        super(errorMessage, cause);
    }
}
