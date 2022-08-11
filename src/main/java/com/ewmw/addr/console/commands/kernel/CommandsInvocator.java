package com.ewmw.addr.console.commands.kernel;

import org.springframework.context.ApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

public class CommandsInvocator {
    protected HashMap<String, Class<ConsoleCommand>> commands;
    protected ApplicationContext springContext;

    public CommandsInvocator(HashMap<String, Class<ConsoleCommand>> commands, ApplicationContext springContext) {
        this.commands = commands;
        this.springContext = springContext;
    }

    public boolean hasCommand(String commandLine) {
        String command = getCommandFromLine(commandLine);

        return commands.keySet().stream().anyMatch(cmd -> cmd.equals(command));
    }

    public void fire(String commandLine) throws Exception {
        Class<ConsoleCommand> commandClass = getCommandClassFromLine(commandLine);

        if (commandClass == null)
            throw new Exception("commandClass not found");

        String[] inputStringArgs = commandLine.trim().replaceAll(" +", " ").split(" ");
        String[] args = Arrays.copyOfRange(inputStringArgs, 1, inputStringArgs.length);


        try {
            ConsoleCommand commandInstance = createCommandInstance(commandClass, args);
            commandInstance.run();
        } catch (Exception e) {
            System.out.println("Fail to fire command");
            e.printStackTrace();
        }

    }

    protected ConsoleCommand createCommandInstance(Class<ConsoleCommand> command, String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        ConsoleCommand bean = null;

        Constructor<?>[] constructors = command.getConstructors();
        Constructor<ConsoleCommand> declaredConstructor = command.getDeclaredConstructor(String[].class);

        bean = declaredConstructor.newInstance((Object) args);

        if (args != null) {
            springContext.getAutowireCapableBeanFactory().autowireBean(bean);
        } else {
            bean = springContext.getBean(command);
        }

        return bean;

    }

    protected String getCommandFromLine(String commandLine) {
        return commandLine.split(" ")[0];
    }

    protected Class<ConsoleCommand> getCommandClassFromLine(String commandLine) throws Exception {
        String command = getCommandFromLine(commandLine);

        String commandName = commands
                .keySet()
                .stream()
                .filter(cmd -> cmd.equals(command))
                .findFirst()
                .orElse(null);

        if (commandName == null)
            throw new Exception("Command not found");

        return commands.get(commandName);
    }
}
