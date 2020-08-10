import view.Menu;
import view.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface(System.in, System.out);
        new Menu(ui).run();
    }

}
