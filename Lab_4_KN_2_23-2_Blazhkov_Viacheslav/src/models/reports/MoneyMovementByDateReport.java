package models.reports;

import models.Account;
import models.Client;
import models.Transaction;
import models.interfaces.Report;

import java.util.ArrayList;

public class MoneyMovementByDateReport implements Report {
    private ArrayList<Client> _clients;
    private String _date;

    public MoneyMovementByDateReport(ArrayList<Client> clients, String transactionDate) {
        this._clients = clients;
        _date = transactionDate;
    }

    @Override
    public StringBuilder generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****************\n");
        for (Client user : this._clients) {
            for (Account account : user.getAccounts().values()) {
                for (Transaction transaction : account.getTransactions()) {
                    if (transaction.getTransactionDate().toString().equals(_date)) {
                        builder.append(user.getName() + " " + account.getAccountNumber() + " $" + transaction.getAmount() + "\n");
                    }
                }
            }
        }
        builder.append("*****************\n\n");
        return builder;
    }
}
