package connecter;

import settings.PropertiesSettings;

import java.io.IOException;
import java.sql.*;
import java.util.Map;

public class MySqlConnecter implements IDBConnector{

    private Map<String, String> dbSettings = null;
    private static Connection connection = null;
    private static Statement statement = null;

    public MySqlConnecter() throws SQLException, IOException {
        dbSettings = new PropertiesSettings("db.properties").getSettings();
        connect();
    }

     private void connect() throws SQLException {
        if (connection == null) {
            connection = DriverManager.getConnection(
                    this.dbSettings.get("url"),
                    this.dbSettings.get("userName"),
                    this.dbSettings.get("password")
            );
        }

        if (statement == null){
            statement = connection.createStatement();
        }
     }

    public static void close() throws SQLException {
        if (statement != null){
            statement.close();
        }
        if (connection != null){
        connection.close();
        }
    }

    public void execute(String sqlRequest) throws SQLException {
        statement.execute(sqlRequest);
    }

    public  ResultSet executeQuery(String sqlRequest) throws SQLException {
        return statement.executeQuery(sqlRequest);
    }
}
