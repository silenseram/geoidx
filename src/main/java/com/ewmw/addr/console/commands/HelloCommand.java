package com.ewmw.addr.console.commands;

import com.ewmw.addr.config.services.GarConfig;
import com.ewmw.addr.console.commands.kernel.AbstractConsoleCommand;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@NoArgsConstructor
public class HelloCommand extends AbstractConsoleCommand {
    @Autowired
    GarConfig garConfig;

    public HelloCommand(String[] args) {
        super(args);
    }

    @Override
    public void run() throws Exception {
        System.out.println(garConfig.getAsAddhouseTypesPrefix());
    }
}
