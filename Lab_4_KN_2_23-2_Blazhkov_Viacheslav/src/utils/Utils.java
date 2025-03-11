package utils;

import models.Account;
import models.Bank;
import models.Client;
import models.TransactionDate;
import models.interfaces.ReportPrinter;
import models.reports.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    static Bank bank = new Bank();
    static Scanner scan = new Scanner(System.in);
    static ReportPrinter printer;
    private static ArrayList<String> clientNames = new ArrayList<>(Arrays.asList("oastedbagelwithcreamcheese",
            "baeconandeggz", "FartinLutherKing", "coolshirtbra",
            "REVERANDTOAST", "kim_chi", "idrinkchocolatemilk",
            "chin_chillin", "ghostfacegangsta", "bigfootisreal",
            "santas_number1_elf", "thehornoftheunicorn"));

    public static void init() {
        int i = 1;
        for (String clientName : clientNames) {
            long amount = 100 * i;
            Client client = bank.registryClient(clientName, clientName + "@hnu.com");
            String number = client.registryAccount();
            client.depositMoney(number, amount, new TransactionDate(i, 2, 2022));
            i++;
        }
        System.out.println("---- printer mode - console");
        printer = new ConsoleReportPrinter();
    }

    public static void printClientInfo() throws IOException {
        StringBuilder report = new StringBuilder();
        report.append("---- existing clients\n");
        var clients = bank.getClients();
        for (Client client : clients) {
            var accounts = client.getAccounts();
            if (accounts.size() == 0) {
                report.append(client.toString());
                report.append("\n");
            } else {
                for (Account account : accounts.values()) {
                    report.append(client.toString() + " " + account.toString());
                    report.append("\n");
                }
            }
            report.append("-----------------------------\n");
        }
        printer.print(report);
    }

    public static void createClient() {
        String clientName;
        do {
            System.out.println("Please enter clientName or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            if (clientInput == null || clientInput == "") {
                System.out.println("Please enter clientName or 'b' to back main menu");
                continue;
            }
            clientName = clientInput;
            break;
        } while (true);
        String email;
        do {
            System.out.println("Please enter email or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            if (clientInput == null || clientInput == "") {
                System.out.println("Please enter email or 'b' to back main menu");
                continue;
            }
            email = clientInput;
            String regex = "^(.+)@(.+)$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                System.out.println("Email is not valid");
                continue;
            }
            clientName = clientName;
            break;
        } while (true);
        Client client = bank.registryClient(clientName, email);
        System.out.println("---------- client '" + client + "' has been created");
    }

    public static void createAccount() {
        Client client;
        do {
            long clientId = 0;
            System.out.println("Please enter client id or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                clientId = Long.parseLong(clientInput);
                client = bank.findClientById(clientId);
                if (client == null) {
                    System.out.println("Client does not exist");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (client == null);
        String number = client.registryAccount();
        System.out.println("---------- account '" + number + "' has been created");
    }

    public static void printTotalAmountOfMoney() throws IOException {
        StringBuilder report = new StringBuilder();
        report.append("----total amount of money\n");
        report.append("balance=$" + bank.getTotalAmount());
        printer.print(report);
    }

    public static void updateMoney(boolean isWithdraw) {
        Client client;
        do {
            long clientId = 0;
            System.out.println("Please enter client id or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                clientId = Long.parseLong(clientInput);
                client = bank.findClientById(clientId);
                if (client == null) {
                    System.out.println("Client does not exist");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (client == null);
        Account account = null;
        do {
            System.out.println("Please enter account number or 'b' to back main menu");
            String number = scan.nextLine();
            if (number.equalsIgnoreCase("b")) {
                return;
            }
            account = client.findAccountByNumber(number);
            if (account == null) {
                System.out.println("----------- account does not exist");
            }
        } while (account == null);
        long amount = 0;
        do {
            System.out.println("Please enter amount or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                amount = Long.parseLong(clientInput);
                if (isWithdraw && amount > account.getBalance()) {
                    System.out.println("Not enough funds");
                    continue;
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);
        int year;
        do {
            System.out.println("Please enter year of transaction or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                year = Integer.parseInt(clientInput);
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);
        int month;
        do {
            System.out.println("Please enter month of transaction or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                month = Integer.parseInt(clientInput);
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);
        int day;
        do {
            System.out.println("Please enter day of transaction or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                day = Integer.parseInt(clientInput);
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                continue;
            }
            break;
        } while (true);
        account.depositMoney(amount, new TransactionDate(day, month, year));
        System.out.println("-------------- new balance - " + account.getBalance());
    }

    public static void setConsolePrintMode() {
        printer = new ConsoleReportPrinter();
        System.out.println("------------ System will print all data in console");
    }

    public static void setFilePrintMode() {
        printer = new FileReportPrinter();
        System.out.println("------------ System will print all data in file");
    }

    public static void printMoneyMovementByClient() throws IOException {
        Client client;
        do {
            long clientId = 0;
            System.out.println("Please enter client id or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                clientId = Long.parseLong(clientInput);
                client = bank.findClientById(clientId);
                if (client == null) {
                    System.out.println("Client does not exist");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (client == null);
        MoneyMovementByClientReport report = new MoneyMovementByClientReport(client);
        printer.print(report.generate());
    }

    public static void printMoneyMovementByDateReport() throws IOException {
        String date;
        do {
            System.out.println("Please enter date (dd-MM-yyyy) or 'b' to back main menu");
            String dateInput = scan.nextLine();
            if (dateInput.equalsIgnoreCase("b")) {
                return;
            }
            String regex = "^\\d{1,2}-\\d{1,2}-\\d{4}$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(dateInput);
            if (matcher.matches()) {
                date = dateInput;
                break;
            }
            System.out.println("Invalid date format!");
        } while (true);
        MoneyMovementByDateReport report = new MoneyMovementByDateReport(bank.getClients(), date);
        printer.print(report.generate());
    }

    public static void printMoneyMovementByAmountReport() throws IOException {
        long amount;
        do {
            System.out.println("Please enter amount or 'b' to back main menu");
            String amountInput = scan.nextLine();
            if (amountInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                amount = Long.parseLong(amountInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid amount format!");
            }
        } while (true);
        MoneyMovementByAmountReport report = new MoneyMovementByAmountReport(bank.getClients(), amount);
        printer.print(report.generate());
    }

    public static void deleteClient() throws IOException {
        Client client;
        do {
            long clientId = 0;
            System.out.println("Please enter client id or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                clientId = Long.parseLong(clientInput);
                client = bank.findClientById(clientId);
                if (client == null) {
                    System.out.println("Client does not exist");
                    continue;
                }
                bank.deleteClient(client);
                System.out.println("Client " + client.getName() + " has been deleted");
                break;
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (true);
    }

    public static void printMoneyMovementByAccount() throws IOException {
        Client client;
        do {
            long clientId = 0;
            System.out.println("Please enter client id or 'b' to back main menu");
            String clientInput = scan.nextLine();
            if (clientInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                clientId = Long.parseLong(clientInput);
                client = bank.findClientById(clientId);
                if (client == null) {
                    System.out.println("Client does not exist");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (client == null);
        Account account;
        do {
            System.out.println("Please enter account number or 'b' to back main menu");
            String accountInput = scan.nextLine();
            if (accountInput.equalsIgnoreCase("b")) {
                return;
            }
            try {
                account = client.findAccountByNumber(accountInput);
                if (account == null) {
                    System.out.println("Account does not exist");
                }
            } catch (NumberFormatException exc) {
                System.out.println("Integer value expected!");
                return;
            }
        } while (account == null);

        MoneyMovementByClientReport report = new MoneyMovementByClientReport(client);
        printer.print(report.generate());
    }

    public static void printMoneyMovementByAllAccountsReport() throws IOException {
        MoneyMovementByAllAccountsReport report = new MoneyMovementByAllAccountsReport(bank.getClients());
        printer.print(report.generate());
    }
}
