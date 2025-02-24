package models;

public class Transaction {
    private long amount;
    private String transactionType;

    public Transaction(long amount, String transactionType) {
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public long getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
