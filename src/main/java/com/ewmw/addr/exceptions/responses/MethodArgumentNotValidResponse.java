package com.ewmw.addr.exceptions.responses;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class MethodArgumentNotValidResponse extends ApiErrorResponse {
    @Getter @Setter
    protected String fieldName;

    public MethodArgumentNotValidResponse(String fieldName, String errorMessage) {
        this.fieldName = fieldName;

        this.message = errorMessage;
        this.responseStatusCode = HttpStatus.UNPROCESSABLE_ENTITY;
    }
}
