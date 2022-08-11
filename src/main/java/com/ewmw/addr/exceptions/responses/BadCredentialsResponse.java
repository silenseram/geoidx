package com.ewmw.addr.exceptions.responses;

public class BadCredentialsResponse extends ApiErrorResponse {
    public BadCredentialsResponse() {
        this.message = "Bad credentials provided";
    }
}
