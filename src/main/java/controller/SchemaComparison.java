package controller;

import model.Database;
import model.Table;
import view.UserInterface;

import java.sql.SQLException;
import java.util.Set;
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

    public void compareTables(Set<String> deletedTables, Set<String> newTables) {

        ui.println(String.format("%d table(s) were deleted, and %d new table(s) were added.", deletedTables.size(), newTables.size()));
        if (deletedTables.size() > 0) {
            ui.println("Deleted:");
            for (String deletedTable : deletedTables) {
                ui.printData(deletedTable);
            }
        }
        if (newTables.size() > 0) {
            ui.println("Added:");
            for (String newTable : newTables) {
                ui.printData(newTable);
            }
        }

    }

    public void getModifiedTables() {
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
        compareTables(deletedTables, newTables);
    }


}
