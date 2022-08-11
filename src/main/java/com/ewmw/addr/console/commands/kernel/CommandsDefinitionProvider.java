package com.ewmw.addr.console.commands.kernel;

import java.util.HashMap;

public class CommandsDefinitionProvider implements CommandsProvider {
    protected HashMap<String, Class<ConsoleCommand>> commandsDefinition;

    public CommandsDefinitionProvider() {
        this.commandsDefinition = new HashMap<>();
    }

    @Override
    public HashMap<String, Class<ConsoleCommand>> getCommandsDefinition() {
        return this.commandsDefinition;
    }

    @Override
    public <T extends ConsoleCommand> void register(String commandName, Class<T> commandClass) {
        commandsDefinition.put(commandName, (Class<ConsoleCommand>) commandClass);
    }


}
