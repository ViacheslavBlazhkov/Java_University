package models;

import java.util.ArrayList;

public class Account {
    private String _accountNumber;
    private ArrayList<Transaction> _transactions;

    public Account(String accountNumber) {
        this._accountNumber = accountNumber;
        this._transactions = new ArrayList<>();
    }

    public String getAccountNumber() {
        return _accountNumber;
    }

    public ArrayList<Transaction> getTransactions() {
        return _transactions;
    }

    public void depositMoney(long amount, TransactionDate transactionDate) {
        long positiveAmount = Math.abs(amount);
        Transaction transaction = new Transaction(positiveAmount, transactionDate);
        addTransaction(transaction);
    }

    public void addTransaction(Transaction transaction) {
        _transactions.add(transaction);
    }

    public long getBalance() {
        long balance = 0;
        for (Transaction transaction : _transactions) {
            balance = balance + transaction.getAmount();
        }
        return balance;
    }

    @Override
    public String toString() {
        return String.format("#%s $%d", _accountNumber, getBalance());
    }
}
