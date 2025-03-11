package models;

public class Transaction {
    private long _amount;
    private TransactionDate _transactionDate;

    public Transaction(long amount, TransactionDate transactionDate) {
        _amount = amount;
        _transactionDate = transactionDate;
    }

    public long getAmount() {
        return _amount;
    }

    public TransactionDate getTransactionDate() {
        return _transactionDate;
    }
}
