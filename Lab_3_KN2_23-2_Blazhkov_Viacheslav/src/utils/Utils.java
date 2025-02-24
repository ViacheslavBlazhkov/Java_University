package utils;

import constants.AccountType;
import constants.TransactionType;
import exceptions.AccountIsBlockedException;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import jdk.jshell.spi.ExecutionControl;
import models.Account;
import models.Bank;
import models.Transaction;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Utils {
    static Bank bank = new Bank();
    public static Scanner scanner = new Scanner(System.in);

    public static float calculateMonthlyInterest(int months, float rate, long amount) {
        return (amount * rate * months) / 12;
    }

    public static void init() throws Exception {
        for (int i = 0; i < 10; i++) {
            long amount = 100 * i;
            Random rand = new Random();
            int accountType = rand.nextInt(1, 3);
            long number = bank.registryAccount(AccountType.getAccountType(accountType));
            bank.updateMoney(number, amount, TransactionType.DEPOSIT);
        }
    }

    public static void printAccountsInfo() {
        System.out.println("---- existing accounts  ----");
        ArrayList<Account> accounts = bank.getAccounts();
        for (Account account : accounts) {
            System.out.println("Account # - '" + account.getNumber() + "', balance = " + account.getBalance());
        }
        System.out.println("---------------------------");
    }

    public static void printAccountsInfoByAmount(boolean isGreater, long amount) {
        System.out.println("---- existing accounts  ----");
        ArrayList<Account> accounts = bank.getAccounts();
        for (Account account : accounts) {
            if (isGreater && account.getBalance() > amount || !isGreater && account.getBalance() < amount) {
                System.out.println("Account # - '" + account.getNumber() + "', balance = " + account.getBalance());
            }
        }
        System.out.println("---------------------------");
    }

    public static void printBlockedAccounts() {
        System.out.println("---- blocked accounts  ----");
        ArrayList<Account> accounts = bank.getAccounts();
        for (Account account : accounts) {
            if (account.isBlocked()) {
                System.out.println("Account # - '" + account.getNumber() + "'");
            }
        }
    }

    public static void createAccount() throws ExecutionControl.NotImplementedException {
        int accountType;
        do {
            String userInput = getFromConsole("Please enter account type (Regular - 1, Gold - 2, Platinum - 3) or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                accountType = Integer.parseInt(userInput);
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (accountType != 1 && accountType != 2 && accountType != 3);
        long accountNumber = bank.registryAccount(AccountType.getAccountType(accountType));
        System.out.println("--------- account '" + accountNumber + "' has been created ---------");
    }

    public static void printTransactionByAccountNumber() {
        long accountNumber;
        Account account;
        do {
            String userInput = getFromConsole("Please enter account number or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                accountNumber = Long.parseLong(userInput);
                account = bank.findAccountByNumber(accountNumber);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
            } catch (AccountNotFoundException e) {
                System.out.println(e.getMessage());
            }
        } while (true);

        System.out.println("-------- transactions: -");
        ArrayList<Transaction> transactions = account.getTransactions();
        for (Transaction transaction : transactions) {
            long amount = transaction.getAmount();
            String transactionType = transaction.getTransactionType();
            System.out.println("---------- " + transactionType + " - " + amount);
        }
        System.out.println("---------------------------------------");
    }

    public static void updateBalance(String trType) throws Exception {
        long accountNumber;
        Account account;
        do {
            String userInput = getFromConsole("Please enter account number or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                accountNumber = Long.parseLong(userInput);
                account = bank.findAccountByNumber(accountNumber);
                if (account.isBlocked()) {
                    throw new AccountIsBlockedException();
                }
            } catch (AccountNotFoundException | AccountIsBlockedException e) {
                System.out.println(e.getMessage());
                continue;
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);

        long amount;
        do {
            String userInput = getFromConsole("Please enter amount or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                amount = Long.parseLong(userInput);
                if (amount < 0 && !trType.equals(TransactionType.SET)) {
                    System.out.println("Enter positive value!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);
        try {
            bank.updateMoney(accountNumber, amount, trType);
            System.out.println("------- new balance - " + account.getBalance());
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printMonthlyInterests(boolean isYearly, boolean isForAll) throws AccountNotFoundException {
        long accountNumber;
        ArrayList<Account> accounts = new ArrayList<>();
        if (!isForAll) {
            do {
                String userInput = getFromConsole("Please enter account number or 'b' to back main menu: ");
                if (userInput.equalsIgnoreCase("b")) {
                    return;
                }
                try {
                    accountNumber = Long.parseLong(userInput);
                    Account account = bank.findAccountByNumber(accountNumber);
                    accounts.add(account);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Integer value expected!");
                } catch (AccountNotFoundException e) {
                    System.out.println(e.getMessage());
                }

            } while (true);
        } else {
            accounts = bank.getAccounts();
        }
        int months = 12;
        if (!isYearly) {
            do {
                String userInput = getFromConsole("Please enter number of months or 'b' to back main menu: ");
                if (userInput.equalsIgnoreCase("b")) {
                    return;
                }
                try {
                    months = Integer.parseInt(userInput);
                    if (months < 1) {
                        System.out.println("Enter positive value!");
                    } else {
                        break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } while (true);
        }
        for (Account account : accounts) {
            System.out.println("Account '" + account.getNumber() + "' | " + account.getMonthlyInterest(months));
        }
    }

    public static void printTotalAmountInBank() {
        System.out.println("------- total amount in bank - " + bank.getTotalAmount());
    }

    public static void printCountTransactionOfAccount() {
        for (Account account : bank.getAccounts()) {
            System.out.println("------- account '" + account.getNumber() + "' has " + account.getTransactions().size() + " transactions -------");
        }
    }

    public static void blockAccount(boolean isBlocked) throws AccountNotFoundException {
        long accountNumber;
        Account account;
        do {
            String userInput = getFromConsole("Please enter account number or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                accountNumber = Long.parseLong(userInput);
                account = bank.findAccountByNumber(accountNumber);
                if (account.isBlocked() == isBlocked) {
                    System.out.println("------- account is already " + (isBlocked ? "blocked" : "unblocked") + " -------");
                    break;
                }
                account.setBlocked(isBlocked);
                System.out.println("------- account " + (isBlocked ? "blocked" : "unblocked") + " -------");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
            } catch (AccountNotFoundException e) {
                System.out.println(e.getMessage());
            }

        } while (true);
        printBlockedAccounts();
        System.out.println("---------------------------------------");
    }

    public static void transfer() throws Exception {
        long accountNumber1, accountNumber2;
        Account account, account2;
        do {
            String userInput = getFromConsole("Please enter first account number or 'b' to back main menu: ");
            if (userInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                accountNumber1 = Long.parseLong(userInput);
                account = bank.findAccountByNumber(accountNumber1);
                if (account.isBlocked()) {
                    throw new AccountIsBlockedException();
                }

                userInput = getFromConsole("Please enter second account number or 'b' to back main menu: ");
                if (userInput.equalsIgnoreCase("b")) {
                    return;
                }
                accountNumber2 = Long.parseLong(userInput);
                account2 = bank.findAccountByNumber(accountNumber2);
                if (account2.isBlocked()) {
                    throw new AccountIsBlockedException();
                }

                userInput = getFromConsole("Please enter amount or 'b' to back main menu: ");
                if (userInput.equalsIgnoreCase("b")) {
                    return;
                }
                long amount = Long.parseLong(userInput);
                if (amount < 0) {
                    System.out.println("Enter positive value!");
                }
                bank.transfer(account, account2, amount);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Integer value expected!");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
        System.out.println("---------------------------------------");
    }

    private static String getFromConsole(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

}
