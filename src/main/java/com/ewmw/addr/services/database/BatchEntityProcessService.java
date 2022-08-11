package com.ewmw.addr.services.database;

public interface BatchEntityProcessService<T> {
    void process(int page, int batchSize);

    long count();
}
