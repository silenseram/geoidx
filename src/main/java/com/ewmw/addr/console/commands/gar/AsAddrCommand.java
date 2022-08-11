package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.gar.asobj.GarProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsAddrCommand implements ConsoleCommand {
    @Autowired
    GarProcessor garProcessor;

    @Override
    public void run() throws Exception {
        garProcessor.process();
    }
}
