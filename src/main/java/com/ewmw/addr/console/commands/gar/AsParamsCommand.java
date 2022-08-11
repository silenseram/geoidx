package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.gar.asparams.AsParamsTypesProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsParamsCommand implements ConsoleCommand {
    @Autowired
    AsParamsTypesProcessor processor;

    @Override
    public void run() throws Exception {
        processor.process();
    }
}
