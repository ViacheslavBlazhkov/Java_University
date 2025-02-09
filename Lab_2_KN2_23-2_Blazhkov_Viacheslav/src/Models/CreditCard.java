package Models;

import Exceptions.NotEnoughFundsException;
import Repositories.CreditCardRepository;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class CreditCard {

    private String _cardNumber;
    private String _cardHolder;
    private String _pinCode;
    private String _cardDate;
    private String _cardCVV;
    private double _balance = 0;
    private boolean _isBlocked = false;

    public CreditCard(String cardHolder, String pinCode) {
        _cardNumber = generateCardNumber();
        _cardHolder = cardHolder;
        _pinCode = pinCode;
        _cardDate = GenerateCardDate();
        _cardCVV = GenerateRandomNumbers(3);
    }

    public CreditCard(String cardHolder, String pinCode, double balance) {
        _cardNumber = generateCardNumber();
        _cardHolder = cardHolder;
        _pinCode = pinCode;
        _cardDate = GenerateCardDate();
        _cardCVV = GenerateRandomNumbers(3);
        _balance = balance;
    }

    public Map<String, String> getInfo() {
        Map<String, String> info = new HashMap<String, String>();
        info.put("number", getCardNumber());
        info.put("holder", getCardHolder());
        info.put("date", getCardDate());
        info.put("cvv", getCardCVV());
        info.put("balance", String.valueOf(getBalance()));
        return info;
    }

    public void refillCard(double amount) {
        _balance += amount;
    }

    public void withdraw(double amount) throws NotEnoughFundsException {
        if (_balance <= amount) {
            throw new NotEnoughFundsException("Картка");
        }
        _balance -= amount;
    }

    public String getCardNumber() {
        return _cardNumber;
    }

    public String getCardHolder() {
        return _cardHolder;
    }

    public String getCardDate() {
        return _cardDate;
    }

    public String getCardCVV() {
        return _cardCVV;
    }

    public double getBalance() {
        return _balance;
    }

    public boolean isBlocked() {
        return _isBlocked;
    }

    public void block(boolean isBlocked) {
        this._isBlocked = isBlocked;
    }

    public boolean checkPinCode(String pinCode) {
        return pinCode.equals(_pinCode);
    }

    public void transfer(double amount, CreditCard card) throws Exception {
        if(card.getCardNumber().equals(this.getCardNumber())) {
            throw new Exception("Однакові номери карт!");
        }
        withdraw(amount);
        card.refillCard(amount);
    }

    private String GenerateRandomNumbers(int count) {
        String SALTCHARS = "1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < count; i++) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        return salt.toString();
    }

    private String GenerateCardDate() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 2);
        Date updatedDate = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(updatedDate);
    }

    public static String generateCardNumber() {
        String cardNumber;
        List<CreditCard> existedCards = CreditCardRepository.getInstance().creditCards;
        boolean isCardNumberExisted;
        do {
            isCardNumberExisted = false;
            cardNumber = "";
            for (int i = 0; i < 16; i++) {
                int digit = (int) (Math.random() * 10);
                cardNumber += digit;
                if (i % 4 == 3 && i != 15) {
                    cardNumber += "-";
                }
            }
            for (CreditCard card : existedCards) {
                if (cardNumber.equals(card.getCardNumber())) {
                    isCardNumberExisted = true;
                    break;
                }
            }
        } while (!isCardNumberValid(cardNumber) || isCardNumberExisted);
        return cardNumber;
    }

    public static boolean isCardNumberValid(String cardNumber) {
        Pattern pattern = Pattern.compile("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$");
        Matcher matcher = pattern.matcher(cardNumber);
        if (!matcher.matches()) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < cardNumber.length(); i++) {
            int digit =
                    Character.getNumericValue(cardNumber.charAt(i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
        }
        return sum % 10 == 0;
    }
}
