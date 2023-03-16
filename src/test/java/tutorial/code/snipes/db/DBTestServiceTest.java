package tutorial.code.snipes.db;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DBTestServiceTests {
    /**
     * Testet die Methode {@link DBTestService#createStm(Connection)}.
     * Überprüft, ob das Statement korrekt erstellt wird und ob es ausgeführt und geschlossen wird.
     */
    @Test
    @DisplayName("Teste die createStm-Methode mit korrektem Statement")
    void testCreateStm_withValidStatement() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        ResultSet resultSet = mock(ResultSet.class);
        doNothing().when(resultSet).close();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        doNothing().when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        dbTestService.createStm(connection);
        verify(connection).createStatement();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).close();
    }
    /**
     * Testet die Methode {@link DBTestService#createStm(Connection)}.
     * Überprüft, ob eine SQLException ausgelöst wird, wenn ResultSet#close() fehlschlägt.
     */
    @Test
    @DisplayName("Teste die createStm-Methode mit fehlerhaftem ResultSet#close()")
    void testCreateStm_withResultSetCloseFailure() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        ResultSet resultSet = mock(ResultSet.class);
        doThrow(new SQLException()).when(resultSet).close();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenReturn(resultSet);
        doNothing().when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        assertThrows(SQLException.class, () -> dbTestService.createStm(connection));
        verify(connection).createStatement();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
        verify(resultSet).close();
    }
    /**
     * Testet die Methode {@link DBTestService#createStm(Connection)}.
     * Überprüft, ob eine IllegalArgumentException ausgelöst wird, wenn Statement#executeQuery(String) fehlschlägt.
     */
    @Test
    @DisplayName("Teste die createStm-Methode mit fehlerhaftem Statement#executeQuery(String)")
    void testCreateStm_withStatementExecuteQueryFailure() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        Statement statement = mock(Statement.class);
        when(statement.executeQuery((String) any())).thenThrow(new IllegalArgumentException());
        doThrow(new IllegalArgumentException()).when(statement).close();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenReturn(statement);
        assertThrows(IllegalArgumentException.class, () -> dbTestService.createStm(connection));
        verify(connection).createStatement();
        verify(statement).executeQuery((String) any());
        verify(statement).close();
    }
    /**
     * Testet die Methode {@link DBTestService#createStm(Connection)}.
     * Überprüft, ob eine SQLException ausgelöst wird, wenn Connection#createStatement() fehlschlägt.
     */
    @Test
    @DisplayName("Teste die createStm-Methode mit fehlerhaftem Connection#createStatement()")
    void testCreateStm_withConnectionCreateStatementFailure() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new SQLException());
        assertThrows(SQLException.class, () -> dbTestService.createStm(connection));
        verify(connection).createStatement();
    }
    /**
     * Testet die Methode {@link DBTestService#showData(ResultSet)}.
     * Überprüft, ob eine IllegalArgumentException ausgelöst wird, wenn ResultSet null ist.
     */
    @Test
    @DisplayName("Teste die showData-Methode mit null-ResultSet")
    void testShowData_withNullResultSet() throws SQLException {
        assertThrows(IllegalArgumentException.class, () -> (new DBTestService()).showData(null));
    }
    /**
     * Testet die Methode {@link DBTestService#showData(ResultSet)}.
     * Überprüft, ob die Methode die Ergebnisse des ResultSet richtig ausgibt.
     */
    @Test
    @DisplayName("Teste die showData-Methode mit korrektem ResultSet")
    void testShowData_withValidResultSet() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenReturn("String");
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        dbTestService.showData(resultSet);
        verify(resultSet, atLeast(1)).next();
        verify(resultSet, atLeast(1)).getString(anyInt());
    }
    /**
     * Testet die Methode {@link DBTestService#showData(ResultSet)}.
     * Überprüft, ob eine SQLException ausgelöst wird, wenn ResultSet#getInt(int) fehlschlägt.
     */
    @Test
    @DisplayName("Teste die showData-Methode mit fehlerhaftem ResultSet#getInt(int)")
    void testShowData_withResultSetGetIntFailure() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getString(anyInt())).thenThrow(new SQLException());
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        assertThrows(SQLException.class, () -> dbTestService.showData(resultSet));
        verify(resultSet).next();
        verify(resultSet).getString(anyInt());
    }
}