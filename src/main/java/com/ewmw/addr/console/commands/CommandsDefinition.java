package com.ewmw.addr.console.commands;

import com.ewmw.addr.console.commands.dumpers.users.UsersLimitsDumperCommand;
import com.ewmw.addr.console.commands.gar.*;
import com.ewmw.addr.console.commands.kernel.CommandsDefinitionProvider;
import com.ewmw.addr.console.commands.osm.Osm2GarAddrCommand;
import com.ewmw.addr.database.migrations.kernel.commands.MigrateCommand;
import com.ewmw.addr.database.migrations.kernel.commands.MigrateInitCommand;

public class CommandsDefinition {
    public static CommandsDefinitionProvider provider = new CommandsDefinitionProvider();

    static {
        provider.register("xsd", XsdCommand.class);
        provider.register("hello", HelloCommand.class);
        provider.register("migrate-init", MigrateInitCommand.class);
        provider.register("migrate", MigrateCommand.class);

        provider.register("dumpers:users:limits", UsersLimitsDumperCommand.class);

        provider.register("gar:asaddr", AsAddrCommand.class);
        provider.register("gar:house", AsHouseCommand.class);
        provider.register("gar:hier", AsHierarchyCommand.class);
        provider.register("gar:path", AsPathCommand.class);
        provider.register("gar:params", AsParamsCommand.class);

        provider.register("osm:addr", Osm2GarAddrCommand.class);
    }
}
