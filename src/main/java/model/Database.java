package model;

import controller.DataManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private String name;
    private List<Table> tables = new ArrayList<Table>();


    public Database(String name, DataManager dataManager) throws SQLException {
        this.name = name;
        dataManager.setTablesForDatabase(this);
    }

    public String getName() {
        return name;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void addTables(Table newTable) {
        this.tables.add(newTable);
    }


}
