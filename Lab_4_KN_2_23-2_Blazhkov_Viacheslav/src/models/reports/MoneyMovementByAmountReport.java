package models.reports;

import models.Account;
import models.Client;
import models.Transaction;
import models.interfaces.Report;

import java.util.ArrayList;

public class MoneyMovementByAmountReport implements Report {
    private ArrayList<Client> _clients;
    private long _amount;

    public MoneyMovementByAmountReport(ArrayList<Client> clients, long amount) {
        this._clients = clients;
        _amount = amount;
    }

    @Override
    public StringBuilder generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****************\n");
        for (Client user : this._clients) {
            for (Account account : user.getAccounts().values()) {
                for (Transaction transaction : account.getTransactions()) {
                    if (transaction.getAmount() == _amount) {
                        builder.append(user.getName() + " " + transaction.getTransactionDate() + " " + account.getAccountNumber() + " $" + transaction.getAmount() + "\n");
                    }
                }
            }
        }
        builder.append("*****************\n\n");
        return builder;
    }
}
