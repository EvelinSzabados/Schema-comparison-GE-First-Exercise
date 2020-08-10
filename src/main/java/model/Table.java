package model;

import java.util.List;

public class Table {

    private String name;
    private String databaseName;
    private List<Column> columns;

    public Table(String name,String databaseName){
        this.name = name;
        this.databaseName = databaseName;
    }

    public String getName(){
        return this.name;
    }

    public List<Column> getColumns(){
        return this.columns;
    }
    public void addColumns(Column newCol ){
        this.columns.add(newCol);
    }
}
