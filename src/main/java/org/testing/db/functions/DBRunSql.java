package org.testing.db.functions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBRunSql {

     public static void main(String[] args) throws SQLException {
         DBTestService dbTestService = new DBTestService();
         DBTestController dbTestController = new DBTestController(dbTestService);
         Connection connection = dbTestController.connectToDb();
         ResultSet resultSet = dbTestController.createStm(connection);
         //  dbTestController.showData(dbTestController.createStm(dbTestController.connectToDb()));
         dbTestController.showData(resultSet);
         dbTestController.closeConnection(connection);
     }
}
