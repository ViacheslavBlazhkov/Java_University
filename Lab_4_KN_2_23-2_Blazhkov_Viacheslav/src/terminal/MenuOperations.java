package terminal;

import java.io.*;
import java.util.Scanner;

public class MenuOperations {
    private static String menuFile = new String("menu.txt");
    public static Scanner scanner = new Scanner(System.in);

    public static void GenerateMenu() {
        try {
            FileWriter file = new FileWriter(menuFile);
            BufferedWriter fileOut = new BufferedWriter(file);
            StringBuilder menu = new StringBuilder();
            menu.append("\n**************** Main Menu *******************\n");
            menu.append("1 - Print clients, accounts and their balance (amount of money)\n");
            menu.append("2 - Create client\n");
            menu.append("3 - Create account for client\n");
            menu.append("4 - Print total amount of money in bank\n");
            menu.append("5 - Deposit money\n");
            menu.append("6 - Use console print mode\n");
            menu.append("7 - Print report about client money movement\n");
            menu.append("8 - Print report about all accounts money movement\n");
            menu.append("********************************************************************\n");
            menu.append("9 - Use file print mode\n");
            menu.append("10 - Print report about account money movement\n");
            menu.append("11 - Withdraw money\n");
            menu.append("********************************************************************\n");
            menu.append("12 - Delete client\n");
            menu.append("13 - Print report about money movement by date\n");
            menu.append("14 - Print report about money movement by amount\n");
            menu.append("********************************************************************\n");
            menu.append("Press 'Q' or 'q' to exit\n");
            menu.append("Please enter number of operation:\n");
            fileOut.write(menu.toString());
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Cannot open file to write");
        } catch (Exception exc) {
            System.out.println("Cannot generate menu");
        }
    }

    public static void PrintMenu() {
        try {
            FileReader file = new FileReader(menuFile);
            BufferedReader fileIn = new BufferedReader(file);
            String line;
            while ((line = fileIn.readLine()) != null) {
                System.out.println(line);
            }
            fileIn.close();
        } catch (IOException e) {
            System.out.println("Cannot open file to read");
        } catch (Exception exc) {
            System.out.println("Cannot print menu");
        }
    }
}
