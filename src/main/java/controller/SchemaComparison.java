package controller;

import model.Column;
import model.Database;
import model.Table;
import view.UserInterface;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class SchemaComparison {

    Database database1;
    Database database2;
    UserInterface ui;

    public SchemaComparison(Database database1, Database database2, UserInterface userInterface) throws SQLException {
        this.database1 = database1;
        this.database2 = database2;
        this.ui = userInterface;
    }

    public void printComparedTables(Set<String> deletedTables, Set<String> newTables) {

        ui.println(String.format("%d table(s) were deleted, and %d new table(s) were added.", deletedTables.size(), newTables.size()));
        if (deletedTables.size() > 0) {
            ui.println("Deleted (or renamed):");
            for (String deletedTable : deletedTables) {
                ui.printData(deletedTable);
            }
        }
        if (newTables.size() > 0) {
            ui.println("Added (or renamed):");
            for (String newTable : newTables) {
                ui.printData(newTable);
            }
        }

    }

    public void printComparedColumns(List<Column> comparedColumns, Database database) {
        if(database.equals(database1)){
            ui.println("\nModified columns:");
        }else{
            ui.println("\nModifications or new columns in database2:");
        }
        ui.printHeader("Column_name (data_type, max_character_length, is_nullable)");

        for (Column col : comparedColumns) {
            ui.printData(col.getName() + "(" + col.getDataType() + ", " +
                    col.getMaxCharLength() + ", " + col.isNullable() + ")");
        }
    }

    public void getModifiedTables() throws IllegalAccessException {
        //Convert list of tables to list of table's name, than convert it to Set
        Set<String> tablesOfDatabase1 = database1.getTables().stream().map(Table::getName).collect(Collectors.toSet());
        Set<String> tablesOfDatabase2 = database2.getTables().stream().map(Table::getName).collect(Collectors.toSet());

        //Get deleted tables by searching values that are in database1 but not in database2
        Set<String> deletedTables = tablesOfDatabase1.stream()
                .filter(tableName -> !tablesOfDatabase2.contains(tableName))
                .collect(Collectors.toSet());
        //Get new tables by searching values that are in database2 but not in database1
        Set<String> newTables = tablesOfDatabase2.stream()
                .filter(tableName -> !tablesOfDatabase1.contains(tableName))
                .collect(Collectors.toSet());

        printComparedTables(deletedTables, newTables);
        getModifiedColumns(getCommonTables(tablesOfDatabase1, tablesOfDatabase2));

    }

    private Set<String> getCommonTables(Set<String> db1, Set<String> db2) {
        return db2.stream()
                .filter(db1::contains)
                .collect(Collectors.toSet());
    }

    private void getModifiedColumns(Set<String> commonTables) throws IllegalAccessException {
        List<Column> deleted = new ArrayList<>();
        List<Column> newCol = new ArrayList<>();

        if (commonTables != null) {
            for (String tableName : commonTables) {
                List<Column> db1Columns = getTableColumnsByTableName(tableName, database1);
                List<Column> db2Columns = getTableColumnsByTableName(tableName, database2);
                assert db1Columns != null;
                assert db2Columns != null;

                deleted.addAll(db1Columns);
                newCol.addAll(db2Columns);
                deleted.removeAll(db2Columns);
                newCol.removeAll(db1Columns);

            }
            printComparedColumns(deleted,database1);
            printComparedColumns(newCol,database2);
        }
    }

    private List<Column> getTableColumnsByTableName(String name, Database database) {

        for (Table table : database.getTables()) {
            if (name.equals(table.getName())) {
                return table.getColumns();
            }
        }
        return null;
    }



}
