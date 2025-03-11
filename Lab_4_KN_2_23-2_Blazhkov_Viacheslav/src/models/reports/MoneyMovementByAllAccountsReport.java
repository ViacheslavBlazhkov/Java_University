package models.reports;

import models.Account;
import models.Client;
import models.Transaction;
import models.interfaces.Report;

import java.util.ArrayList;

public class MoneyMovementByAllAccountsReport implements Report {
    private ArrayList<Client> _clients;

    public MoneyMovementByAllAccountsReport(ArrayList<Client> clients) {
        this._clients = clients;
    }

    @Override
    public StringBuilder generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****************\n");
        for (Client user : this._clients) {
            for (Account account : user.getAccounts().values()) {
                for (Transaction transaction : account.getTransactions()) {
                    builder.append(user.getName() + " " + account.getAccountNumber() + " " + transaction.getTransactionDate() + " $" + transaction.getAmount() + "\n");
                }
            }
        }
        builder.append("*****************\n\n");
        return builder;
    }
}
