package Models;

import Exceptions.CreditCardIsBlockedException;
import Exceptions.NotEnoughFundsException;

public class ATM {
    private Bank _bank;
    private double _balance = 0;

    public ATM(Bank bank, double initialBalance) {
        _bank = bank;
        _balance = initialBalance;
        bank._atms.add(this);
    }

    public double getBalance() {
        return _balance;
    }

    public void refillCard(CreditCard card, double amount) {
        card.refillCard(amount);
        _balance += amount;
        _bank.refill(amount);
    }

    public void withdraw(CreditCard card, double amount) throws NotEnoughFundsException, CreditCardIsBlockedException {
        if (card.isBlocked()) {
            throw new CreditCardIsBlockedException();
        }
        if (card.getBalance() < amount) {
            throw new NotEnoughFundsException("Карта");
        }
        if (getBalance() < amount) {
            throw new NotEnoughFundsException("Банкомат");
        }
        _bank.withdraw(amount);
        card.withdraw(amount);
        _balance -= amount;
    }
}
