package models.storages;

import exceptions.AccountNotFoundException;
import models.Account;

import java.util.ArrayList;

public class Accounts {
    protected ArrayList<Account> accounts = new ArrayList<>();

    public void AddAccount(Account account) {
        accounts.add(account);
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public Account FindAccountByNumber(long number) throws AccountNotFoundException {
        for (Account account : accounts) {
            if (account.getNumber() == number) {
                return account;
            }
        }
        throw new AccountNotFoundException();
    }
}
