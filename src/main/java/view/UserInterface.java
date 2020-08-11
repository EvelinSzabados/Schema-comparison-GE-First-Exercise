package view;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class UserInterface {
    Scanner scanner;
    PrintStream out;
    String RESET = "\u001B[0m";
    String BLUE = "\u001B[94m";
    String GREEN = "\u001B[32m";

    public UserInterface(InputStream in, PrintStream out) {
        this.scanner = new Scanner(in);
        this.out = out;
    }

    public void println(Object obj) { out.println(BLUE + obj + RESET);
    }
    public void printData(Object obj){out.println(obj);}
   public void printHeader(Object obj){out.println(GREEN + obj + RESET);}
}
