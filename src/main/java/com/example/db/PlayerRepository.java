package com.example.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlayerRepository {

    public void create(String name) throws SQLException {
        String trimmed = name == null ? "" : name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Name darf nicht leer sein.");
        }

        try (Connection c = Db.open();
                PreparedStatement ps = c.prepareStatement("INSERT INTO players(name) VALUES (?)")) {
            ps.setString(1, trimmed);
            ps.executeUpdate();
        }
    }

    public boolean existsByName(String name) throws SQLException {
        try (Connection c = Db.open();
                PreparedStatement ps = c.prepareStatement("SELECT 1 FROM players WHERE name = ? LIMIT 1")) {
            ps.setString(1, name.trim());
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
}
