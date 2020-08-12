package controller;

import model.Column;
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
            database.addTables(new Table(rs.getString("table_name"),database.getName(),this));
        }
        rs.close();
    }
    public void setTableRowsNumber(Table table) throws SQLException {
        String query = "SELECT COUNT(*) AS rows FROM information_schema.tables WHERE table_name=? AND table_schema=?";

        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, table.getName());
        st.setString(2, table.getDatabaseName());


        ResultSet rs = st.executeQuery();
        rs.first();
        table.setNumberOfRows(rs.getInt("rows"));
    }

    public void setColumnsForTable(Table table) throws SQLException {

        String query = "SELECT column_name," +
                "is_nullable," +
                "data_type," +
                "character_maximum_length " +
                "FROM information_schema.columns " +
                "WHERE table_schema = ? and table_name = ?;";

        PreparedStatement st = conn.prepareStatement(query);
        st.setString(1, table.getDatabaseName());
        st.setString(2, table.getName());

        ResultSet rs = st.executeQuery();

        while(rs.next()){
            String name = rs.getString("column_name");
            String dataType = rs.getString("data_type");
            boolean isNullable = rs.getBoolean("is_nullable");
            int maxLength = rs.getInt("character_maximum_length");

            table.addColumns(new Column(name,dataType,maxLength,isNullable,table.getName()));
        }
        rs.close();
    }
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:mariadb://localhost/information_schema", "evelin", "password");
    }
}
