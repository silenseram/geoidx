package com.ewmw.addr.services.osm.processor.addr;

import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.models.reposotories.GarAddrRepository;
import com.ewmw.addr.services.address.types.AddrTypesManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;

public class GarAddrBatchProcessor implements Runnable {
    @Autowired
    GarAddrRepository repository;

    private int page;

    private int batchSize;

    @Autowired
    AddrTypesManager addrTypesManager;


    public GarAddrBatchProcessor(int page, int batchSize) {
        this.page = page;
        this.batchSize = batchSize;
    }

    @Override
    public void run() {
        List<GarAddr> withOffsetAndLimit = repository.findWithOffsetAndLimit(PageRequest.of(page, batchSize));

        withOffsetAndLimit.forEach(garAddr -> {
            HashMap<String, Object> objectParams = addrTypesManager.getObjectParams(garAddr);
            System.out.println("su4ka");
        });

        System.out.println("#" + Thread.currentThread().getId() + ", page: " + page + ", batchSize: " + batchSize + ", rows fetched: " + withOffsetAndLimit.size());

    }


}
