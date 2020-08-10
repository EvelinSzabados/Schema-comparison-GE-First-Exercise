package controller;

import model.Database;
import model.Table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class SchemaComparison {

    Database database1;
    Database database2;

    public SchemaComparison(Database database1,Database database2) throws SQLException {
        this.database1 = database1;
        this.database2 = database2;
    }



}
