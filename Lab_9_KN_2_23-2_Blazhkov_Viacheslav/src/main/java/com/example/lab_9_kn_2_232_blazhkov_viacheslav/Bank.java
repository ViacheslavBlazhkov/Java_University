package com.example.lab_9_kn_2_232_blazhkov_viacheslav;

import java.util.ArrayList;

public class Bank {
    public ArrayList<Client> clients;// список клієнтів

    public Bank() {
        clients = new ArrayList<Client>();
    }

    public ArrayList<Client> getUsers() {
        return clients;// вибірка уcіх клієнтів
    }

    public Client registryClient(String name, String email) {
        long userId = 1000 + this.clients.size(); // генерація номера рахунка
        Client user = new Client(name, userId, email);// створення рахунка
        this.clients.add(user); // додавання рахунка
        return user;
    }

    public Client findUserId(long userId) {
        for (Client user : clients) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public long getTotalAmount() {
        long balance = 0;
        for (Client user : clients) {
            balance = balance + user.getUserMoney();
        }
        return balance;
    }

    public void depositMoney(long userId, long amount, TransactionDate transactionDate) {
        Client user = findUserId(userId);
        if (user == null) {
            System.out.println("***User not found");
            return;
        }
        user.depositMoney(amount, transactionDate);
    }

    public void withdrawMoney(long userId, long amount, TransactionDate transactionDate) throws Exception {
        Client user = findUserId(userId);
        if (user == null) {
            System.out.println("***User not found");
            return;
        }
        if(user.getUserMoney() < amount) {
            throw new Exception();
        }
        user.withdrawMoney(amount, transactionDate);
    }

    public void transferMoney(long fromUserId, long toUserId, long amount, TransactionDate transactionDate) throws Exception {
        Client fromUser = findUserId(fromUserId);
        Client toUser = findUserId(toUserId);
        if (fromUser == null) {
            System.out.println("***From User not found");
            return;
        }
        if (toUser == null) {
            System.out.println("***To User not found");
            return;
        }
        if(fromUser.getUserMoney() < amount) {
            throw new Exception();
        }
        fromUser.withdrawMoney(amount, transactionDate);
        toUser.depositMoney(amount, transactionDate);
    }

}
