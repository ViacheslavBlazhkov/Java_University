package Exceptions;

public class CreditCardNotFoundException extends Exception {
    public CreditCardNotFoundException() {
        super("Кредитну картку не знайдено");
    }
}