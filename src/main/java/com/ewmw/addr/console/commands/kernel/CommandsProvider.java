package com.ewmw.addr.console.commands.kernel;

import java.util.HashMap;

public interface CommandsProvider {
    public HashMap<String, Class<ConsoleCommand>> getCommandsDefinition();

    public <T extends ConsoleCommand> void register(String commandName, Class<T> commandClass);
}
