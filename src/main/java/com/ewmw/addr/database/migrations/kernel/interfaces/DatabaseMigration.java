package com.ewmw.addr.database.migrations.kernel.interfaces;

public interface DatabaseMigration {
    public String getApplyQuery();
    public String getRevertQuery();

    public String getIdentifier();
    public boolean isApplied();
}
