package com.ewmw.addr.database.migrations.kernel.services;

import com.ewmw.addr.database.migrations.kernel.Migration;
import com.ewmw.addr.database.migrations.kernel.MigrationHolder;
import com.ewmw.addr.database.migrations.kernel.MigrationsRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

@Service
public class MigrationsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MigrationsRepository repository;

    public MigrationsService() {
    }

    public void createMigrationsTable() throws IOException {
        String query = IOUtils.toString(new FileReader("src\\main\\java\\com\\example\\springboot\\database\\migrations\\kernel\\init.sql"));

        jdbcTemplate.execute(query);
    }

    public boolean isApplied(String migrationName) {
        List<Migration> migrations = repository.findMigrationByName(migrationName);

        if (migrations.size() < 1)
            return false;

        return migrations.get(0).isApplied();
    }

    public void applyMigration(MigrationHolder migration) {
        String query = migration.getQueryUp();
        jdbcTemplate.execute(query);
    }

    public void saveApplied(String migrationName) {
        Migration migration = new Migration();

        migration.setName(migrationName);
        migration.setApplied(true);

        repository.save(migration);
    }

    public void revertMigration(MigrationHolder migration) {
        String query = migration.getQueryDown();
        jdbcTemplate.execute(query);
    }
}
