package tutorial.code.snipes.db;

import java.sql.*;

public class DBTestService {
    public static final String DB_HOST = "1.2.3.4";
    public static final String PORT = "1521";
    public static final String DB_SERVICE = "pdborcl";
    public static final String DB_USER = "hr";
    public static final String DB_PASSWORD = "xxx";
    public static final String JDBC_ORACLE = "jdbc:oracle:thin:@" + DB_HOST + ":" + PORT + "/" + DB_SERVICE;
    private Connection conn = null;
    private String sql = "SELECT * FROM EMPLOYEES";

    public DBTestService() {
    }
    public Connection connectToDb() {
        try {
            conn = DriverManager.getConnection(JDBC_ORACLE, DB_USER, DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet createStm(Connection conn) throws SQLException {
        try (Statement stm = conn.createStatement();
             ResultSet rs = stm.executeQuery(sql)) {
            return rs;
        }
    }
    public void showData(ResultSet rs ) throws SQLException {
        if(rs == null ) {
            throw new IllegalArgumentException("ResultSet is null");
        }
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }
    public Connection getConnection() {
        return conn;
    }
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

