package models;

import java.util.HashMap;

public class Client {
    private long _id;
    private String _name;
    private HashMap<String, Account> _accounts;
    private String _email;

    public Client(long id, String name, String email) {
        _id = id;
        _name = name;
        _email = email;
        _accounts = new HashMap<>();
    }

    public long getClientMoney() {
        long balance = 0;
        for (Account account : _accounts.values()) {
            balance = balance + account.getBalance();
        }
        return balance;
    }

    public String registryAccount() {
        String number = "1000" + (this._accounts.size() + 1000) + _id;
        Account account = new Account(number);
        _accounts.put(number, account);
        return number;
    }

    public void depositMoney(String number, long amount, TransactionDate transactionDate) {
        Account account = findAccountByNumber(number);
        if (account == null) {
            System.out.println("************ Account not found");
            return;
        }
        account.depositMoney(amount, transactionDate);
    }

    public HashMap<String, Account> getAccounts() {
        return _accounts;
    }

    public String getEmail() {
        return _email;
    }

    public long getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    @Override
    public String toString() {
        return _name + '/' + _email + "(id=" + _id + ")";
    }

    public Account findAccountByNumber(String accountNumber) {
        return _accounts.get(accountNumber);
    }
}
