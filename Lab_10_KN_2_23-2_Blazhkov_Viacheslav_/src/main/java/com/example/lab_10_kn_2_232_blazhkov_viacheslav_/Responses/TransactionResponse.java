package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Responses;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models.Transaction;

import java.util.List;

public class TransactionResponse {
    private final Long clientId;
    private final List<Transaction> transactions;

    public TransactionResponse(Long clientId, List<Transaction> transactions) {
        this.clientId = clientId;
        this.transactions = transactions;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

}
