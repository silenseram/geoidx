package com.ewmw.addr.console.commands.gar;

import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.services.gar.paths.PathsProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsPathCommand implements ConsoleCommand {
    @Autowired
    PathsProcessor pathsProcessor;

    @Override
    public void run() throws Exception {
        pathsProcessor.process();
    }
}
