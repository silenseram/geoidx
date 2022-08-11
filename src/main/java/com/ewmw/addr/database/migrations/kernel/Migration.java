package com.ewmw.addr.database.migrations.kernel;

import com.ewmw.addr.database.migrations.kernel.interfaces.DatabaseMigration;
import com.ewmw.addr.database.migrations.MigrationsDefinition;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = Migration.MODEL_NAME)
public class Migration implements DatabaseMigration {
    public static final String MODEL_NAME = "migration";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "applied", nullable = false)
    private boolean applied;

    @Override
    public String getApplyQuery() {
        return MigrationsDefinition.migrations.get(this.name).getQueryUp();
    }

    @Override
    public String getRevertQuery() {
        return MigrationsDefinition.migrations.get(this.name).getQueryDown();
    }

    @Override
    public String getIdentifier() {
        return id.toString();
    }

    @Override
    public boolean isApplied() {
        return this.applied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }
}
