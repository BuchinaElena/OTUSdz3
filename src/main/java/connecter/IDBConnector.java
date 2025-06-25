package connecter;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDBConnector {

    void execute(String sqlRequest) throws SQLException;

    static ResultSet executeQuery(String sqlRequest) throws SQLException {
        return null;
    }
}
