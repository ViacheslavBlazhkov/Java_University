package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models;

public class Transaction {
    private String type;
    private long amount;
    private TransactionDate transactionDate;

    public Transaction(String type, long amount, TransactionDate transactionDate) {
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public long getAmount() {
        return amount; // cума
    }

    public TransactionDate getTransactionDate() {
        return transactionDate; // дата транзакції
    }

    public String getType() {
        return type;
    }
}
