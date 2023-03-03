package org.testing.db.functions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

class DBTestServiceTest {
    /**
     * Teste folgende Methode: {@link DBTestService#createStm(Connection)}
     */
    @Test
    void testCreateStm() throws SQLException {
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
     * Teste folgende Methode: {@link DBTestService#createStm(Connection)}
     */
    @Test
    void testCreateStm2() throws SQLException {
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
     * Teste folgende Methode: {@link DBTestService#createStm(Connection)}
     */
    @Test
    void testCreateStm3() throws SQLException {
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
     * Teste folgende Methode: {@link DBTestService#createStm(Connection)}
     */
    @Test
    void testCreateStm4() throws SQLException {
        DBTestService dbTestService = new DBTestService();
        Connection connection = mock(Connection.class);
        when(connection.createStatement()).thenThrow(new SQLException());
        assertThrows(SQLException.class, () -> dbTestService.createStm(connection));
        verify(connection).createStatement();
    }
}

