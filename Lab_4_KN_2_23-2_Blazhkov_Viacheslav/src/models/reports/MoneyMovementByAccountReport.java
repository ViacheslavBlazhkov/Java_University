package models.reports;

import models.Account;
import models.Transaction;
import models.interfaces.Report;

public class MoneyMovementByAccountReport implements Report {
    private Account _account;

    public MoneyMovementByAccountReport(Account account) {
        this._account = account;
    }

    @Override
    public StringBuilder generate() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n*****************\n");
        for (Transaction transaction : _account.getTransactions()) {
            builder.append(transaction.getTransactionDate() + " " + _account.getAccountNumber() + " $" + transaction.getAmount() + "\n");
        }
        builder.append("*****************\n\n");
        return builder;
    }
}
