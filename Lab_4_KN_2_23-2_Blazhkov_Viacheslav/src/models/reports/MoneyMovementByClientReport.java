package models.reports;

import models.Account;
import models.Client;
import models.Transaction;
import models.interfaces.Report;

public class MoneyMovementByClientReport implements Report {
    private Client _client;

    public MoneyMovementByClientReport(Client client) {
        this._client = client;
    }

    @Override
    public StringBuilder generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****************\n");
        for (Account account : _client.getAccounts().values()) {
            for (Transaction transaction : account.getTransactions()) {
                builder.append(transaction.getTransactionDate() + " " + account.getAccountNumber() + " $" + transaction.getAmount() + "\n");
            }
        }
        builder.append("*****************\n\n");
        return builder;
    }
}
