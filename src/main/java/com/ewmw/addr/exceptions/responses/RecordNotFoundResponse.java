package com.ewmw.addr.exceptions.responses;

import org.springframework.http.HttpStatus;

public class RecordNotFoundResponse extends ApiErrorResponse {
    protected Long identifier;
    protected String model;

    public RecordNotFoundResponse() {
        this.init();
    }

    public RecordNotFoundResponse(Long identifier) {
        this.identifier = identifier;
    }

    public RecordNotFoundResponse(Long identifier, String model) {
        this.identifier = identifier;
        this.model = model;

        this.init();
    }

    protected void init() {
        this.responseStatusCode = HttpStatus.NOT_FOUND;
        this.message = "Requested record not found";
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
