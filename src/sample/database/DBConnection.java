package sample.database;

import java.sql.*;


public class DBConnection {

    public static Connection DB_CONN = null;

    public void connect() {

        try {
            // lib : Driver : nome do arquivo
            String url = "jdbc:sqlite:dbsqlite";
            // create a connection to the database
            DB_CONN = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
