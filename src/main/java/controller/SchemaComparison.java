package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SchemaComparison {
    private static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost/information_schema", "evelin", "password");
    }

}
