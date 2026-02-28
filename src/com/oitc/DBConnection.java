package com.oitc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection getConnection() {

        try {
            return DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/tutorbot?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
                "root",
                "Anto@13"
            );

        } catch (Exception e) {
            throw new RuntimeException("Database connection failed: " + e.getMessage());
        }
    }
}