package org.testing.db.functions;

import java.sql.*;

public class DBTestService {
    public static final String DB_HOST = "192.168.1.113";
    public static final String PORT = "1521";
    public static final String DB_SERVICE = "pdborcl";
    public static final String DB_USER = "hr";
    public static final String DB_PASSWORD = "Admin123";
    public static final String JDBC_ORACLE = "jdbc:oracle:thin:@" + DB_HOST + ":" + PORT + "/" + DB_SERVICE;
    private Connection conn = null;
    private String sql = "SELECT * FROM EMPLOYEES";

    public DBTestService() {
    }
    public Connection connectToDb() {
        try {
                conn = DriverManager.getConnection(JDBC_ORACLE, DB_USER, DB_PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
    public ResultSet createStm(Connection conn) throws SQLException {
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(sql);
        return rs ;
    }
    public void showData(ResultSet rs, ResultSet resultSet) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }
    public Connection getConnection() {
        return conn;
    }
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

