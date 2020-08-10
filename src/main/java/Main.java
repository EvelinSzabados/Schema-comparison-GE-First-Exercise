import controller.DataManager;
import controller.SchemaComparison;
import model.Database;
import view.Menu;
import view.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataManager dataManager = new DataManager();
        new Database("test",dataManager);
        new Database("test2",dataManager);

        UserInterface ui = new UserInterface(System.in, System.out);
        new Menu(ui).run();
    }

}
