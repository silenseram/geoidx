package com.ewmw.addr.services.database;

import com.ewmw.addr.models.gar.GarHouse;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class AsyncTableProcessorService<T> {
    private final int batchSize = 10000;

    public void handle(BatchEntityProcessService<T> entityService) {
        int page = -1;
        long rowsAmount = entityService.count();


        while ((long) page * batchSize < rowsAmount) {
            page++;
            entityService.process(page, batchSize);
        }
    }
}
