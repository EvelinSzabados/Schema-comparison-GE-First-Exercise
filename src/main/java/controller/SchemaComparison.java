package controller;

import model.Database;
import model.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SchemaComparison {

    Database database1;
    Database database2;

    public SchemaComparison(Database database1, Database database2) throws SQLException {
        this.database1 = database1;
        this.database2 = database2;
    }

    public String compareTables() {

        if (database1.getTables().size() < database2.getTables().size()) {
            return "New table were added.";
        } else if (database1.getTables().size() > database2.getTables().size()) {
            return "Table were deleted";
        } else {
            return "No tables were added or deleted.";
        }
    }

    public void getModifiedTables() {
        Set<String> tablesOfDatabase1 = database1.getTables().stream().map(Table::getName).collect(Collectors.toSet());
        Set<String> tablesOfDatabase2 = database2.getTables().stream().map(Table::getName).collect(Collectors.toSet());

        Set<String> deletedTables = tablesOfDatabase1.stream()
                .filter(tableName -> !tablesOfDatabase2.contains(tableName))
                .collect(Collectors.toSet());

        Set<String> newTables = tablesOfDatabase2.stream()
                .filter(tableName -> !tablesOfDatabase1.contains(tableName))
                .collect(Collectors.toSet());


    }


}
