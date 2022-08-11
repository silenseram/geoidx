package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.gar.hierarchy.ObjectsHierarchyProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AsHierarchyCommand implements ConsoleCommand {
    @Autowired
    ObjectsHierarchyProcessor processor;

    @Override
    public void run() throws Exception {
        processor.process();
    }
}
