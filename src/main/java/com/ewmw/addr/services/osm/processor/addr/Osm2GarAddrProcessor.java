package com.ewmw.addr.services.osm.processor.addr;

import com.ewmw.addr.interfaces.Processor;
import com.ewmw.addr.models.gar.GarAddr;
import com.ewmw.addr.services.beans.SpringBeansService;
import com.ewmw.addr.services.database.AsyncTableProcessorService;
import com.ewmw.addr.services.osm.processor.GarAddrTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Osm2GarAddrProcessor<T> implements Processor {
    @Autowired
    SpringBeansService beansService;

    @Override
    public void process() {
        AsyncTableProcessorService<GarAddr> garAddrAsyncTableProcessorService = new AsyncTableProcessorService<>();

        try {
            garAddrAsyncTableProcessorService.handle(beansService.instantiateNew(GarAddrTableService.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
