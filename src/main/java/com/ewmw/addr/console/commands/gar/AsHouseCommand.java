package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.gar.ashouse.AsHouseProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsHouseCommand implements ConsoleCommand {
    @Autowired
    AsHouseProcessor houseProcessor;

    @Override
    public void run() throws Exception {
        houseProcessor.process();
    }
}
