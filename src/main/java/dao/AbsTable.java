package dao;

import connecter.MySqlConnecter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbsTable {
    protected String tableName;
    protected MySqlConnecter connecter;
    protected Map<String, String> columns = new HashMap<>();

    public AbsTable(String tableName, MySqlConnecter connecter) {
        this.tableName = tableName;
        this.connecter = connecter;
    }

    public void create() {
        String sqlRequest = String.format("CREATE TABLE IF NOT EXISTS %s (%s)", this.tableName, convertMapColumnsToString());
        try {
           connecter.execute(sqlRequest);
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
