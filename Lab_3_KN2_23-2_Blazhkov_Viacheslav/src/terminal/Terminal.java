package terminal;

import constants.TransactionType;
import utils.Utils;

import java.util.Scanner;

public class Terminal {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        String commandNumberStr;
        Utils.init();
        while (true) {
            System.out.println("\n******************* Main Menu ***********");
            System.out.println("1 - Create account");
            System.out.println("2 - Print accounts and their balance (amount of money)");
            System.out.println("3 - Deposit money");
            System.out.println("4 - Print all transactions by account number");
            System.out.println("5 - Print annual interests by account number");
            System.out.println("6 - Print total amount of money in bank");
            System.out.println("7 - Print count of transactions in each account");
            System.out.println("-------------------------------------------------------");
            System.out.println("8 - Withdraw money");
            System.out.println("9 -  Print all accounts with minus");
            System.out.println("10 - Update account balance");
            System.out.println("11 - Create account with Platinum type");
            System.out.println("-------------------------------------------------------");
            System.out.println("12 - Block account");
            System.out.println("13 - Transfer money between accounts");
            System.out.println("14 - Print interests for accounts");
            System.out.println("15 - Unlock a blocked account");
            System.out.println("***********************************************");
            System.out.println("Press 'Q' or 'q' to exit\n");
            System.out.println("Please enter number of operation:");

            commandNumberStr = scanner.nextLine();
            if (commandNumberStr.equals("Q") || commandNumberStr.equals("q")) {
                break;
            }
            switch (commandNumberStr) {
                case "1":
                case "11": {
                    Utils.createAccount();
                    break;
                }
                case "2": {
                    Utils.printAccountsInfo();
                    break;
                }
                case "3": {
                    Utils.updateBalance(TransactionType.DEPOSIT);
                    break;
                }
                case "4": {
                    Utils.printTransactionByAccountNumber();
                    break;
                }
                case "5": {
                    Utils.printMonthlyInterests(true, false);
                    break;
                }
                case "6": {
                    Utils.printTotalAmountInBank();
                    break;
                }
                case "7": {
                    Utils.printCountTransactionOfAccount();
                    break;
                }
                case "8": {
                    Utils.updateBalance(TransactionType.WITHDRAW);
                    break;
                }
                case "9": {
                    Utils.printAccountsInfoByAmount(false, 0);
                    break;
                }
                case "10": {
                    Utils.updateBalance(TransactionType.SET);
                    break;
                }
                case "12": {
                    Utils.blockAccount(true);
                    break;
                }
                case "13": {
                    Utils.transfer();
                    break;
                }
                case "14": {
                    Utils.printMonthlyInterests(false, false);
                    break;
                }
                case "15": {
                    Utils.blockAccount(false);
                    break;
                }
                default: {
                    System.out.println("Not supported command");
                    break;
                }
            }
        }
        System.out.println("See you soon! Bye, Bye!");
        scanner.close();
    }
}
