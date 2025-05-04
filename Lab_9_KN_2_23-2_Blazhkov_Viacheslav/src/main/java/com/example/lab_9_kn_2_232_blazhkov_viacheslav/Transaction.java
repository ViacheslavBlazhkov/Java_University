package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import lombok.Getter;

@Getter
public class Transaction {
    private final String type;
    private final long amount;
    private final TransactionDate transactionDate;

    public Transaction(String type, long amount, TransactionDate transactionDate) {
        this.type = type;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }
}
