package org.testing.db.functions;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectToDbTest {

    private static final String DB_HOST = "192.168.1.113";
    private static final String PORT = "1521";
    private static final String DB_SERVICE = "pdborcl";
    private static final String DB_USER = "hr";
    private static final String DB_PASSWORD = "Admin123";
    private static final String JDBC_ORACLE = "jdbc:oracle:thin:@" + DB_HOST + ":" + PORT + "/" + DB_SERVICE;

    private DBTestService dbTestService;

    @BeforeEach
    void setUp() {
        dbTestService = new DBTestService();
    }

    @Test
    @DisplayName("Test connectToDb method")
    void testConnectToDb() {
        try (Connection conn = DriverManager.getConnection(JDBC_ORACLE, DB_USER, DB_PASSWORD)) {
            assertNotNull(conn);
            assertTrue(conn.isValid(0));
        } catch (SQLException e) {
            fail("Could not connect to database: " + e.getMessage());
        }
    }
}
