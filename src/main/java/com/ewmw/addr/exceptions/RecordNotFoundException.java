package com.ewmw.addr.exceptions;

public class RecordNotFoundException extends Throwable{
    protected Long identifier;
    protected String model;

    public RecordNotFoundException() {
    }

    public RecordNotFoundException(Long identifier, String model) {
        this.identifier = identifier;
        this.model = model;
    }

    public Long getIdentifier() {
        return identifier;
    }

    public void setIdentifier(Long identifier) {
        this.identifier = identifier;
    }

    public String getModelName() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
