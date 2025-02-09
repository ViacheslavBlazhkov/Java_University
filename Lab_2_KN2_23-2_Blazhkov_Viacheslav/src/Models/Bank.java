package Models;

import Exceptions.NotEnoughFundsException;
import Repositories.CreditCardRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
    private double _balance = 0;
    public List<ATM> _atms;
    private List<CreditCard> _creditCards = new ArrayList<>();

    public Bank(double initialBalance, List<ATM> atms) {
        _balance = initialBalance;
        _atms = atms;
        createTestCreditCards(10);
    }

    public double getBalance() {
        return _balance;
    }

    public void withdraw(double amount) throws NotEnoughFundsException {
        if (getBalance() < amount) {
            throw new NotEnoughFundsException("Банк");
        }
        _balance -= amount;
    }

    public void refill(double amount) {
        _balance += amount;
    }

    public void updateCardBalance(CreditCard card, double amount) {
        card.refillCard(-card.getBalance() + amount);
    }

    public double getTotalBalance() {

        double totalBalance = getBalance();
        for (ATM atm : getATMs()) {
            totalBalance += atm.getBalance();
        }
        for (CreditCard card : _creditCards) {
            totalBalance += card.getBalance();
        }
        return totalBalance;
    }

    public List<ATM> getATMs() {
        return _atms;
    }

    public void setATMs(List<ATM> atms) {
        _atms = atms;
    }

    public CreditCard createCreditCard(String owner, String pinCode) {
        CreditCardRepository repo = CreditCardRepository.getInstance();
        CreditCard card = new CreditCard(owner, pinCode);
        repo.creditCards.add(card);
        _creditCards.add(card);
        return card;
    }

    private void createTestCreditCards(int count) {
        Random rnd = new Random();
        CreditCardRepository repo = CreditCardRepository.getInstance();
        for (int i = 0; i < count; i++) {
            double balance = (double) Math.round(rnd.nextDouble() * 100000) / 100;
            CreditCard card = new CreditCard("Test Owner " + i, "123456", balance);
            repo.creditCards.add(card);
            _creditCards.add(card);
        }
    }
}
