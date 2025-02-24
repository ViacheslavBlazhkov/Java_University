package models;

import constants.TransactionType;
import exceptions.InsufficientFundsException;
import models.storages.Transactions;

import java.util.ArrayList;

public abstract class Account {
    private long number;
    private long balance;
    private Transactions transactions;
    private boolean isBlocked;

    public Account(long number) {
        this.number = number;
        this.transactions = new Transactions();
    }

    public abstract float getMonthlyInterest(int months);

    public void updateMoney(long amount, String trType) throws Exception {
        if ((trType.equals(TransactionType.DEPOSIT) || trType.equals(TransactionType.WITHDRAW)) && amount < 0) {
            throw new Exception("Amount must be positive");
        }
        if (trType.equals(TransactionType.WITHDRAW)) {
            if (balance < amount) {
                throw new InsufficientFundsException();
            }
            balance -= amount;
        } else if (trType.equals(TransactionType.DEPOSIT)) {
            balance += amount;
        } else {
            balance = amount;
        }
        Transaction transaction = new Transaction(amount, trType);
        addTransaction(transaction);
    }

    public long getBalance() {
        return balance;
    }

    private void addTransaction(Transaction transaction) {
        transactions.addTransaction(transaction);
    }

    public long getNumber() {
        return number;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions.getTransactions();
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}
