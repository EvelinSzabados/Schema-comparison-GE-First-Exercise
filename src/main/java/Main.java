import view.UserInterface;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserInterface ui = new UserInterface(System.in, System.out);
        new Main(ui).run();
    }
    UserInterface ui;


    Main(UserInterface ui) throws SQLException {
        this.ui = ui;

    }
    private void run() throws SQLException {

        boolean running = true;

        while (running) {
            ui.printTitle("Menu");
            ui.printOption('a', "Table comparison");
            ui.printOption('b', "Column comparison");
            ui.printOption('q', "Exit");
            switch (ui.choice("abq")) {
                case 'a':
                    ui.printTitle("Shows if new tables were added, or how many tables were deleted.");
                    ui.println("0 tables were added or deleted");
                    break;
                case 'b':
                    ui.printTitle("Shows if columns were modified.");
                    ui.println("0 changes in columns");
                    break;
                case 'q':
                    running = false;
                    ui.println("See you next time!");
                    break;
            }
        }
    }
}
