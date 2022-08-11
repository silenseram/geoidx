package com.ewmw.addr.database.migrations.kernel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MigrationsRepository extends JpaRepository<Migration, Integer> {
    @Query("SELECT m FROM Migration m where m.name=:name")
    List<Migration> findMigrationByName(@Param("name") String name);
}
