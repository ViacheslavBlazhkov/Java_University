package models.storages;

import models.Transaction;

import java.util.ArrayList;

public class Transactions {
    protected ArrayList<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
}
