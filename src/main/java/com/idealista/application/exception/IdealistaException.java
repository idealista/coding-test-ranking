package com.idealista.application.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.idealista.application.exception.dto.ErrorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class IdealistaException extends RuntimeException {

    private HttpStatus httpStatus;
    private String message;
    private ErrorDTO errors;

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
