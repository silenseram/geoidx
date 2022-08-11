package com.ewmw.addr.database.migrations.kernel.commands;

import com.ewmw.addr.database.migrations.kernel.services.MigrationsService;
import com.ewmw.addr.console.commands.kernel.ConsoleCommand;
import com.ewmw.addr.database.migrations.MigrationsDefinition;
import com.ewmw.addr.database.migrations.kernel.MigrationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MigrateCommand implements ConsoleCommand {

    @Autowired
    protected MigrationsService migrationsService;

    @Autowired
    protected MigrationsRepository migrationsRepository;

    @Override
    public void run() {
        MigrationsDefinition.migrations.forEach((migrationName, migrationHolder) -> {
            if (migrationsService.isApplied(migrationName))
                return;

            migrationsService.applyMigration(migrationHolder);
            migrationsService.saveApplied(migrationName);
        });

        System.out.println("All migrated!");
    }
}
