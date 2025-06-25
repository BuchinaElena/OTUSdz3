package dao;

import connecter.MySqlConnecter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsTable {
    protected String tableName;
    protected Map<String, String> columns = new HashMap<>();

    public AbsTable(String tableName) {
        this.tableName = tableName;
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        try {
           MySqlConnecter.executeQuery(sqlRequest);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String convertMapColumnsToString() {
        String result = "";
        for(Map.Entry<String, String> el : columns.entrySet()){
            result += el.getKey() + " " + el.getValue() + ",";
        }
        result = result.substring(0, result.length()-1);
        return result;
    }
}
