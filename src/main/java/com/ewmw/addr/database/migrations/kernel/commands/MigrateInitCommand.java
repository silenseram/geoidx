package com.ewmw.addr.database.migrations.kernel.commands;

import com.ewmw.addr.database.migrations.kernel.services.MigrationsService;
import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MigrateInitCommand implements ConsoleCommand {

    @Autowired
    protected MigrationsService migrationsService;

    @Override
    public void run() {
        try {
            migrationsService.createMigrationsTable();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
