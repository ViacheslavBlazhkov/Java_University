package models;

import java.util.ArrayList;

public class Bank {
    private ArrayList<Client> _clients;

    public Bank() {
        _clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return _clients;
    }

    public Client registryClient(String name, String email) {
        long userId = 1000 + _clients.size();
        Client user = new Client(userId, name, email);
        _clients.add(user);
        return user;
    }

    public Client findClientById(long userId) {
        for (Client user : _clients) {
            if (user.getId() == userId) {
                return user;
            }
        }
        return null;
    }

    public void deleteClient(Client client) {
        _clients.remove(client);
    }

    public long getTotalAmount() {
        long balance = 0;
        for (Client client : _clients) {
            balance = balance + client.getClientMoney();
        }
        return balance;
    }

    public void depositMoney(long clientId, String accountNumber, long amount, TransactionDate transactionDate) {
        Client client = findClientById(clientId);
        if (client == null) {
            System.out.println("******** Client not found");
            return;
        }
        client.depositMoney(accountNumber, amount, transactionDate);
    }
}
