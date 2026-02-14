package com.example.db;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Db {
    private Db() {
    }

    public static Connection open() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:" + dbPath().toAbsolutePath());
    }

    public static Path dbPath() {
        Path dir = Paths.get(System.getProperty("user.home"), ".dartscounter");
        try {
            Files.createDirectories(dir);
        } catch (Exception e) {
            throw new RuntimeException("Could not create app dir: " + dir, e);
        }
        return dir.resolve("dartscounter.db");
    }
}
