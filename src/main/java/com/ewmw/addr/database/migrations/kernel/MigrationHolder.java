package com.ewmw.addr.database.migrations.kernel;

public class MigrationHolder {
    protected String queryUp;
    protected String queryDown;

    public MigrationHolder(String queryUp, String queryDown) {
        this.queryUp = queryUp;
        this.queryDown = queryDown;
    }

    public static MigrationHolder create(String queryUp, String queryDown) {
        return new MigrationHolder(queryUp, queryDown);
    }

    public String getQueryUp() {
        return queryUp;
    }

    public String getQueryDown() {
        return queryDown;
    }
}
