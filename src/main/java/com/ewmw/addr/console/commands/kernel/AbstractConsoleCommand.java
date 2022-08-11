package com.ewmw.addr.console.commands.kernel;

import java.util.Arrays;

public abstract class AbstractConsoleCommand implements HasArguments, ConsoleCommand {
    public String[] args;

    public AbstractConsoleCommand(String... args) {
        this.args = args;
    }

    @Override
    public boolean hasArgument(String argument) {
        return Arrays.asList(args).contains(argument);
    }
}
