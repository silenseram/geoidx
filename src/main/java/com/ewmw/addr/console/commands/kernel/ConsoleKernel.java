package com.ewmw.addr.console.commands.kernel;

import com.ewmw.addr.console.commands.CommandsDefinition;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ConsoleKernel implements CommandLineRunner, ApplicationContextAware {
    protected CommandsInvocator invocator;

    @Autowired
    protected ApplicationContext springContext;

    public ConsoleKernel() {
    }

    @Override
    public void run(String... args) throws Exception {
        invocator = new CommandsInvocator(CommandsDefinition.provider.getCommandsDefinition(), springContext);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String line = scanner.nextLine();

            if (invocator.hasCommand(line))
                invocator.fire(line);
            else
                System.out.println("Command " + line + " not found");
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.springContext = applicationContext;
    }
}
