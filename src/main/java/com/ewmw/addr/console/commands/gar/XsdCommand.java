package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.services.gar.zip.ArchiveProcessor;
import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class XsdCommand implements ConsoleCommand {
    @Autowired
    ArchiveProcessor processor;

    @Override
    public void run() throws Exception {
        processor.process();
    }
}
