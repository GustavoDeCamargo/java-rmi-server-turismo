package sample;

import java.sql.*;


public class DBConnection {


    public void connect() {
        Connection conn = null;

        try {
            // lib : Driver : nome do arquivo
            String url = "jdbc:sqlite:dbsqlite";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");
            Statement stmt  = conn.createStatement();
            ResultSet rs    = stmt.executeQuery("SELECT * FROM teste");

                // loop through the result set
                while (rs.next()) {
                    System.out.println(rs.getString("nome"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
}
