package com.ewmw.addr.interfaces;

public interface Processor {
    <T extends Exception> void process() throws T;
}
