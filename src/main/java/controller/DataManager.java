package controller;

import model.Database;
import model.Table;

import java.sql.*;

public class DataManager {
    private Connection conn = connection();

    public DataManager() throws SQLException {
    }

    public void setTablesForDatabase(Database database) throws SQLException {
        String query = "SELECT table_name FROM information_schema.tables WHERE table_schema=?;";

        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, database.getName());

        ResultSet rs = st.executeQuery();

        while (rs.next()) {
            database.addTables(new Table(rs.getString("table_name"),database.getName()));
        }
        rs.close();
    }

    public void setColumnsForTable(Table table){

    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost/information_schema", "evelin", "password");
    }
}
