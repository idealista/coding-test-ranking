package com.idealista.application.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Data
public class ExceptionDTO {
    private ErrorDTO error;
    private String message;
    private HttpStatus httpStatus;
}
