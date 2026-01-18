package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/Hospital";
    private static final String USER = "amina";
    private static final String PASSWORD = "Aeiinie1";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
