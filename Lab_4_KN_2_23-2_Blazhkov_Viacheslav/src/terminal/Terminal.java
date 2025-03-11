package terminal;

import utils.Utils;

import java.util.Scanner;

public class Terminal {
    public static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        MenuOperations.GenerateMenu();
        String commandNumberStr = null;
        Utils.init();
        do {
            MenuOperations.PrintMenu();
            commandNumberStr = scan.nextLine();
            if (commandNumberStr.equalsIgnoreCase("q")) {
                break;
            }
            switch (commandNumberStr) {
                case "1": {
                    Utils.printClientInfo();
                    break;
                }
                case "2": {
                    Utils.createClient();
                    break;
                }
                case "3": {
                    Utils.createAccount();
                    break;
                }
                case "4": {
                    Utils.printTotalAmountOfMoney();
                    break;
                }
                case "5": {
                    Utils.updateMoney(false);
                    break;
                }
                case "6": {
                    Utils.setConsolePrintMode();
                    break;
                }
                case "7": {
                    Utils.printMoneyMovementByClient();
                    break;
                }
                case "8": {
                    Utils.printMoneyMovementByAllAccountsReport();
                    break;
                }
                case "9": {
                    Utils.setFilePrintMode();
                    break;
                }
                case "10": {
                    Utils.printMoneyMovementByAccount();
                    break;
                }
                case "11": {
                    Utils.updateMoney(true);
                    break;
                }
                case "12": {
                    Utils.deleteClient();
                    break;
                }
                case "13": {
                    Utils.printMoneyMovementByDateReport();
                    break;
                }
                case "14": {
                    Utils.printMoneyMovementByAmountReport();
                    break;
                }
                default: {
                    System.out.println("Not supported command");
                    break;
                }
            }
        } while (true);
        System.out.println("See you soon! Bye, Bye!");
        scan.close();
    }
}
