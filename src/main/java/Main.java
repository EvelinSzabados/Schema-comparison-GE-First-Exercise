import controller.DataManager;
import controller.SchemaComparison;
import model.Database;
import view.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {
        UserInterface ui = new UserInterface(System.in, System.out);
        DataManager dataManager = new DataManager();

        Database database1 = new Database("test", dataManager);
        Database database2 = new Database("test2", dataManager);

        SchemaComparison sc = new SchemaComparison(database1, database2, ui);
        sc.getModifiedTables();



    }

}
