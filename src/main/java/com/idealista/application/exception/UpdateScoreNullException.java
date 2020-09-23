package com.idealista.application.exception;

public class UpdateScoreNullException extends RuntimeException {

    public UpdateScoreNullException() {
        super("Score cannot be updated with null value");
    }
}
