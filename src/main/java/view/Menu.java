package view;

import java.sql.SQLException;

public class Menu {
    UserInterface ui;


    public Menu(UserInterface ui) throws SQLException {
        this.ui = ui;

    }
    public void run() throws SQLException {

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
