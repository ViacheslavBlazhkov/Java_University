package models;

import constants.AccountType;
import constants.TransactionType;
import exceptions.AccountNotFoundException;
import exceptions.InsufficientFundsException;
import models.storages.Accounts;

import java.util.ArrayList;

public class Bank {
    private Accounts accounts = new Accounts();

    public ArrayList<Account> getAccounts() {
        return accounts.getAccounts();
    }

    public long registryAccount(String acType) {
        long accountNumber = generateAccountNumber();
        Account account;
        if (acType.equals(AccountType.GOLD)) {
            account = new GoldAccount(accountNumber);
        } else if (acType.equals(AccountType.REGULAR)) {
            account = new RegularAccount(accountNumber);
        } else if (acType.equals(AccountType.PLATINUM)) {
            account = new PlatinumAccount(accountNumber);
        } else {
            throw new IllegalArgumentException("Invalid account type");
        }
        registryAccount(account);
        return accountNumber;
    }

    public void registryAccount(Account account) {
        accounts.AddAccount(account);
    }

    private long generateAccountNumber() {
        return 1000 + accounts.getAccounts().size();
    }

    public Account findAccountByNumber(long number) throws AccountNotFoundException {
        return accounts.FindAccountByNumber(number);
    }

    public void updateMoney(long accountNumber, long amount, String trType) throws Exception {
        Account account = findAccountByNumber(accountNumber);
        account.updateMoney(amount, trType);
    }

    public void transfer(Account acc1, Account acc2, long amount) throws Exception {
        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException();
        }
        acc1.updateMoney(amount, TransactionType.WITHDRAW);
        acc2.updateMoney(amount, TransactionType.DEPOSIT);
    }

    public long getTotalAmount() {
        long totalAmount = 0;
        ArrayList<Account> accounts = getAccounts();

        for (Account account : accounts) {
            totalAmount += account.getBalance();
        }

        return totalAmount;
    }
}
