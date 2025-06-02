package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models;


import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Exceptions.NotEnoughMoneyException;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.ArrayList;

public class Client {
    @NotBlank(message = "Name is required")
    private String name;
    private long id;
    @NotBlank(message = "Email is required")
    @Email(message = "Email is required")
    private String email;

    private final ArrayList<Transaction> transactions;

    public Client() {
        transactions = new ArrayList<Transaction>();
    }

    public Client(String name, long id, String email) {
        this.name = name;
        this.id = id;
        this.email = email;
        transactions = new ArrayList<Transaction>();
    }

    public long getClientMoney() {
        long balance = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals("deposit")) {
                balance += transaction.getAmount();
            } else {
                balance -= transaction.getAmount();
            }
        }
        return balance;
    }

    public Transaction depositMoney(long amount, TransactionDate transactionDate) {
        long positiveAmount = Math.abs(amount);
        Transaction transaction = new Transaction("deposit", positiveAmount, transactionDate);
        addTransaction(transaction);
        return transaction;
    }

    public Transaction withdrawMoney(long amount, TransactionDate transactionDate) {
        long positiveAmount = Math.abs(amount);
        if (amount > this.getClientMoney()) {
            throw new NotEnoughMoneyException(this.getId());
        }
        Transaction transaction = new Transaction("withdraw", positiveAmount, transactionDate);
        addTransaction(transaction);
        return transaction;
    }

    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public ArrayList<Transaction> getTransactionsByType(String type) {
        var types = new ArrayList<Transaction>();
        for (Transaction transaction : transactions) {
            if (transaction.getType().equals(type)) {
                types.add(transaction);
            }
        }
        return types;
    }

    public ArrayList<Transaction> getTransactionsByDates(LocalDate fromDate, LocalDate toDate) {
        var filteredTransactions = new ArrayList<Transaction>();

        for (Transaction transaction : transactions) {
            TransactionDate transactionDateObj = transaction.getTransactionDate();
            LocalDate transactionDate = LocalDate.of(
                    transactionDateObj.getYear(),
                    transactionDateObj.getMonth(),
                    transactionDateObj.getDay()
            );
            if ((transactionDate.isEqual(fromDate) || transactionDate.isAfter(fromDate)) &&
                    (transactionDate.isEqual(toDate) || transactionDate.isBefore(toDate))) {
                filteredTransactions.add(transaction);
            }
        }

        return filteredTransactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name + '/' + email + "(id=" + id + ")";
    }
}