package com.ewmw.addr.exceptions.responses;

import org.springframework.http.HttpStatus;

public class NotFoundPathResponse extends ApiErrorResponse {
    public NotFoundPathResponse(String path) {
        this.message = "Requested path not found";
        this.responseStatusCode = HttpStatus.NOT_FOUND;
        this.path = path;
    }
}
