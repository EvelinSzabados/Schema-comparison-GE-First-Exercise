import controller.DataManager;
import controller.SchemaComparison;
import model.Column;
import model.Database;
import model.Table;
import view.Menu;
import view.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        DataManager dataManager = new DataManager();
        Database database1 = new Database("test",dataManager);
        Database database2 = new Database("test2",dataManager);

        UserInterface ui = new UserInterface(System.in, System.out);
        new Menu(ui).run();
    }

}
