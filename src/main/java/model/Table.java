package model;

import controller.DataManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private String name;
    private String databaseName;

    private int numberOfRows;
    private List<Column> columns = new ArrayList<Column>();

    public Table(String name, String databaseName, DataManager dataManager) throws SQLException {
        this.name = name;
        this.databaseName = databaseName;
        dataManager.setColumnsForTable(this);
        dataManager.setTableRowsNumber(this);
    }

    public String getName(){
        return this.name;
    }
    public String getDatabaseName() {
        return databaseName;
    }
    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows){
        this.numberOfRows = numberOfRows;
    }

    public List<Column> getColumns(){
        return this.columns;
    }
    public void addColumns(Column newCol ){
        this.columns.add(newCol);
    }
}
