package com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Responses;

import com.example.lab_10_kn_2_232_blazhkov_viacheslav_.Models.Transaction;

public class TransferResponse {
    private final Transaction deposit;
    private final Transaction withdraw;

    public TransferResponse(Transaction deposit, Transaction withdraw) {
        this.deposit = deposit;
        this.withdraw = withdraw;
    }

    public Transaction getDeposit() {
        return deposit;
    }

    public Transaction getWithdraw() {
        return withdraw;
    }
}
