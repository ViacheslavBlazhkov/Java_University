package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Getter
public class Client {

    private final ArrayList<Transaction> transactions; // обороти(транзакції)
    @Setter
    @NotBlank(message = "Name is required")
    private String name;
    private long id;
    @Setter
    @NotBlank(message = "Email is required")
    @Email(message = "Email is required")
    private String email;

    public Client() {
        this.transactions = new ArrayList<Transaction>();
    }

    public Client(String name, long id, String email) {
        this.name = name;
        this.transactions = new ArrayList<Transaction>();
        this.email = email;
        this.id = id;
    }

    public long getUserMoney() {
        long balance = 0;
        for (Transaction transaction : transactions) {
            balance = balance + transaction.getAmount();
        }
        return balance;
    }

    public void depositMoney(long amount, TransactionDate transactionDate) {
        long positiveAmount = Math.abs(amount);
        Transaction transaction = new Transaction("deposit", positiveAmount, transactionDate);
        addTransaction(transaction); // додаємо в колекцію транзакцій
    }

    public void withdrawMoney(long amount, TransactionDate transactionDate) {
        Transaction transaction = new Transaction("withdraw", -amount, transactionDate);
        addTransaction(transaction); // додаємо в колекцію транзакцій
    }

    private void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        return name + "/" + email + "(id=" + id + ")";
    }

    public ArrayList<Transaction> getTransactionsByType(String transactionType) {
        ArrayList<Transaction> transactions = new ArrayList<>();
        for (Transaction transaction : this.transactions) {
            if (transaction.getType().equals(transactionType)) {
                transactions.add(transaction);
            }
        }
        return transactions;
    }
}
