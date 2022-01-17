package com.supinfo.java.day2;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static java.lang.Class.forName;

public class DB {
    static final String DB_URL = "jdbc:h2:.//localhost/";
    static final String USER = "guest";
    static final String PASS = "guest123";
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
        // Open a connection
        try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE DATABASE STUDENTS";
            stmt.executeUpdate(sql);
            System.out.println("Database created successfully...");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}





