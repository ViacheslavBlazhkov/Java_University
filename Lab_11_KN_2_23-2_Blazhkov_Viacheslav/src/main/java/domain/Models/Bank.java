package domain.Models;

import java.util.ArrayList;

public class Bank {
    public ArrayList<Client> clients;

    public Bank() {
        clients = new ArrayList<>();
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public Client registryClient(String name, String email) {
        long clientId = 1000 + clients.size();
        Client client = new Client(name, clientId, email);
        clients.add(client);
        return client;
    }

    public Client findClient(long clientId) {
        for (Client client : clients) {
            if (client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

    public long getTotalAmount() {
        long balance = 0;
        for (Client client : clients) {
            balance = balance + client.getClientMoney();
        }
        return balance;
    }

    public void removeClient(Client client) {
        clients.remove(client);
    }
}
