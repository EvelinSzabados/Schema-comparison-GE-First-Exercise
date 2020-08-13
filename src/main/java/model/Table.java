package model;

import controller.DataManager;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Table {

    private String name;
    private String databaseName;
    private List<Column> columns = new ArrayList<Column>();

    public Table(String name, String databaseName, DataManager dataManager) throws SQLException {
        this.name = name;
        this.databaseName = databaseName;
        dataManager.setColumnsForTable(this);

    }

    public String getName() {
        return this.name;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public List<Column> getColumns() {
        return this.columns;
    }

    public void addColumns(Column newCol) {
        this.columns.add(newCol);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return name.equals(table.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
