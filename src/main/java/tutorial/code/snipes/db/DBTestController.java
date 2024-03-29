package tutorial.code.snipes.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBTestController {
    private DBTestService dbTestService;

    public DBTestController(DBTestService dbTestService) {
        this.dbTestService = dbTestService;
    }
    public Connection connectToDb() {
        return dbTestService.connectToDb();
    }
    public ResultSet createStm(Connection conn) throws SQLException {
        return  dbTestService.createStm(this.connectToDb());
    }
    public void showData(ResultSet rs ) throws SQLException {
        dbTestService.showData(this.createStm(this.connectToDb()));
    }
    public void closeConnection(Connection connection) {
        dbTestService.closeConnection(connection);
    }
}
