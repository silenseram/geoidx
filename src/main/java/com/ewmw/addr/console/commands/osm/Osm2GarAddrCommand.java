package com.ewmw.addr.console.commands.osm;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.osm.processor.addr.Osm2GarAddrProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Osm2GarAddrCommand implements ConsoleCommand {
    @Autowired
    Osm2GarAddrProcessor processor;

    @Override
    public void run() throws Exception {
        processor.process();
    }
}
