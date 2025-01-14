package com.example.fmjavaserver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Component;

@Component
public class DataTransfer {

    public void transferData(String sqlFile) {
        // PostgreSQL connection details
        String pgUrl = "jdbc:postgresql://fmpostgres-fm-postgres-sql.e.aivencloud.com:14782/defaultdb?sslmode=require";
        String pgUser = "avnadmin";
        String pgPassword = "AVNS_wpqwwNFjcGifiEi-T_J";

        Connection pgConn = null;
        Statement pgStmt = null;

        int insertCount = 0; // Count of executed INSERT statements

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");

            // Print current working directory
            System.out.println("Current Working Directory: " + System.getProperty("user.dir"));

            // Connect to PostgreSQL
            pgConn = DriverManager.getConnection(pgUrl, pgUser, pgPassword);
            pgStmt = pgConn.createStatement();

            // Read INSERT statements from file
            try (BufferedReader br = new BufferedReader(new FileReader(sqlFile))) {
                String line;
                StringBuilder query = new StringBuilder();

                while ((line = br.readLine()) != null) {
                    query.append(line);

                    // If line ends with a semicolon, it's the end of an INSERT statement
                    if (line.trim().endsWith(";")) {
                        try {
                            pgStmt.execute(query.toString());
                            insertCount++; // Increment count
                            System.out.println("Executed: " + query);
                        } catch (SQLException e) {
                            System.err.println("Error executing statement: " + query + "\nError: " + e.getMessage());
                        }
                        // Clear the query for the next statement
                        query.setLength(0);
                    }
                }
                System.out.println("Total INSERT statements executed: " + insertCount);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close connections
            try {
                if (pgStmt != null) pgStmt.close();
                if (pgConn != null) pgConn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
