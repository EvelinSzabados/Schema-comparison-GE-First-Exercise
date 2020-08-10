package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataManager {
    public Connection conn;

    public DataManager(Connection connect) {
        this.conn = connect;
    }
}
