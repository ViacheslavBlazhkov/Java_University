package Repositories;

import Exceptions.CreditCardNotFoundException;
import Exceptions.IncorrectPinCodeException;
import Models.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class CreditCardRepository {
    private static volatile CreditCardRepository instance;
    public List<CreditCard> creditCards;

    private CreditCardRepository() {
        creditCards = new ArrayList<>();
    }

    public static CreditCardRepository getInstance() {
        CreditCardRepository localInstance = instance;
        if (localInstance != null) {
            return localInstance;
        }
        synchronized (CreditCardRepository.class) {
            if (instance == null) {
                instance = new CreditCardRepository();
            }
            return instance;
        }
    }

    public CreditCard findCardByNumber(String number) throws CreditCardNotFoundException {
        for (CreditCard card : creditCards) {
            if (card.getCardNumber().equals(number)) {
                return card;
            }
        }
        throw new CreditCardNotFoundException();
    }

    public List<CreditCard> getCardsWithAmount(double amount) {
        List<CreditCard> cards = new ArrayList<>();
        for (CreditCard card : creditCards) {
            if (card.getBalance() >= amount) {
                cards.add(card);
            }
        }
        return cards;
    }

    public List<CreditCard> getBlockedCards() {
        List<CreditCard> cards = new ArrayList<>();
        for (CreditCard card : creditCards) {
            if (card.isBlocked()) {
                cards.add(card);
            }
        }
        return cards;
    }

    public void removeCardByNumber(String number, String pinCode) throws Exception {
        CreditCard card = findCardByNumber(number);
        if (!card.checkPinCode(pinCode)) {
            throw new IncorrectPinCodeException();
        }
        creditCards.remove(card);
    }
}
