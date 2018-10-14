package sample.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static sample.database.DBConnection.DB_CONN;

public class Repository {
    public ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = DB_CONN.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        return rs;
}

    public void start_sn() throws SQLException {
        String sql = "delete from servico_nomes;" +
                "delete from sqlite_sequence where name='servico_nomes';";
        Statement stmt = DB_CONN.createStatement();
        stmt.executeUpdate(sql);
    }

    public void executeUpdate(String sql) throws SQLException {
        Statement stmt = DB_CONN.createStatement();
        stmt.executeUpdate(sql);
    }
}
