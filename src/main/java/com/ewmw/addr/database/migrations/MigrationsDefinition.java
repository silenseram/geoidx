package com.ewmw.addr.database.migrations;

import com.ewmw.addr.database.migrations.kernel.MigrationHolder;
import com.ewmw.addr.utils.FilesHelper;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MigrationsDefinition {
    public static HashMap<String, MigrationHolder> migrations = new HashMap<>();

    static {
        File migrationsDir = new File("src\\main\\java\\com\\ewmw\\addr\\database\\migrations\\list");
        File[] files = migrationsDir.listFiles();

        if (files == null) {
            System.out.println("Cannot find migrations directory: " + migrationsDir.getAbsolutePath());
        }

        Arrays.stream(files)
                        .forEach(file -> {
                            try {
                                String name = file.getName().replaceAll("\\.sql", "");

                                migrations.put(name, MigrationHolder.create(
                                        FilesHelper.getFileContent(file.getAbsolutePath()),
                                        ""
                                ));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

//        migrations.put("users", MigrationHolder.create(
//                "CREATE TABLE user(id BIGINT UNSIGNED AUTO_INCREMENT PRIMARY KEY, first_name VARCHAR(255), last_name VARCHAR(255), email VARCHAR(255) NOT NULL)",
//                "DROP TABLE user"
//        ));
    }
}
