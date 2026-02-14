package com.example.db;

import java.sql.Connection;
import java.sql.Statement;

public final class Schema {
    private Schema() {
    }

    public static void init() {
        try (Connection c = Db.open();
                Statement s = c.createStatement()) {

            s.executeUpdate("""
                        CREATE TABLE IF NOT EXISTS players (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            name TEXT NOT NULL UNIQUE,
                            created_at TEXT NOT NULL DEFAULT (datetime('now'))
                        )
                    """);

        } catch (Exception e) {
            throw new RuntimeException("DB init failed", e);
        }
    }
}
