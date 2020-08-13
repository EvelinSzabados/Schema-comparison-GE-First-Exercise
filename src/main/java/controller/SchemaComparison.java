package controller;

import model.Column;
import model.Database;
import model.Table;
import view.UserInterface;

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

    public void printComparedTables(Set<Table> deletedTables, Set<Table> newTables) {

        ui.println(String.format("%d table(s) were deleted, and %d new table(s) were added.", deletedTables.size(), newTables.size()));
        if (deletedTables.size() > 0) {
            ui.println("Deleted (or renamed):");
            for (Table deletedTable : deletedTables) {
                ui.printData(deletedTable.getName());
            }
        }
        if (newTables.size() > 0) {
            ui.println("Added (or renamed):");
            for (Table newTable : newTables) {
                ui.printData(newTable.getName());
            }
        }

    }

    public void printComparedColumns(List<Column> comparedColumns, Database database) {
        if(database.equals(database1)){
            ui.println("\nColumns modified:");
        }else{
            ui.println("\nList of modifications or new columns:");
        }

        for (Column col : comparedColumns) {
            ui.printData("In table '" + col.getTableName() + "': " + col.getName() + "(" + col.getDataType() + ", " +
                    col.getMaxCharLength() + ", " + col.isNullable() + ")");
        }
    }

    public void getModifiedTables() {

        Set<Table> tablesOfDatabase1 = new HashSet<>(database1.getTables());
        Set<Table> tablesOfDatabase2 = new HashSet<>(database2.getTables());


        Set<Table> deletedTables = tablesOfDatabase1.stream()
                .filter(table -> tablesOfDatabase2.stream().noneMatch(table::equals))
                .collect(Collectors.toSet());

        Set<Table> newTables = tablesOfDatabase2.stream()
                .filter(table -> tablesOfDatabase1.stream().noneMatch(table::equals))
                .collect(Collectors.toSet());

        printComparedTables(deletedTables, newTables);
        getModifiedColumns(getCommonTables(tablesOfDatabase1, tablesOfDatabase2));

    }

    private Set<Table> getCommonTables(Set<Table> db1, Set<Table> db2) {
        return db2.stream()
                .filter(db2Table -> db1.stream().anyMatch(db2Table::equals))
                .collect(Collectors.toSet());
    }

    private void getModifiedColumns(Set<Table> commonTables) {
        List<Column> deletedColumns = new ArrayList<>();
        List<Column> modifiedColumns = new ArrayList<>();

        if (commonTables != null) {
            for (Table table : commonTables) {
                List<Column> db1Columns = getTableColumnsByDatabase(table, database1);
                List<Column> db2Columns = getTableColumnsByDatabase(table, database2);
                assert db1Columns != null;
                assert db2Columns != null;

                deletedColumns.addAll(db1Columns);
                modifiedColumns.addAll(db2Columns);
                deletedColumns.removeAll(db2Columns);
                modifiedColumns.removeAll(db1Columns);

            }
            printComparedColumns(deletedColumns,database1);
            printComparedColumns(modifiedColumns,database2);
        }
    }

    private List<Column> getTableColumnsByDatabase(Table currentTable, Database database) {

        for (Table table : database.getTables()) {
            if (currentTable.equals(table)) {
                return table.getColumns();
            }
        }
        return null;
    }




}
