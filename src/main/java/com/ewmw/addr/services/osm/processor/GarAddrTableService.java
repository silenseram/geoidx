package com.ewmw.addr.services.osm.processor;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.reposotories.GarAddrRepository;
import com.ewmw.addr.services.database.BatchEntityProcessService;
import com.ewmw.addr.services.osm.processor.addr.GarAddrBatchProcessor;
import com.ewmw.addr.services.osm.processor.addr.Osm2GarAddrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class GarAddrTableService implements BatchEntityProcessService<GarAddr> {
    public static final int THREADS_AMOUNT = 8;

    @Autowired
    GarAddrRepository repository;

    @Autowired
    Osm2GarAddrService addrService;

    @Autowired
    private AutowireCapableBeanFactory autowireCapableBeanFactory;

    ThreadPoolExecutor executor;

    public GarAddrTableService() {
        executor =
                (ThreadPoolExecutor) Executors.newFixedThreadPool(THREADS_AMOUNT);
    }

    @Override
    public void process(int page, int batchSize) {
        GarAddrBatchProcessor garAddrBatchProcessor = new GarAddrBatchProcessor(page, batchSize);
        autowireCapableBeanFactory.autowireBean(garAddrBatchProcessor);

        executor.submit(garAddrBatchProcessor);
    }

    @Override
    public long count() {
        return repository.count();
    }
}
