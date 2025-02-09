package Exceptions;

public class CreditCardIsBlockedException extends Exception {
    public CreditCardIsBlockedException() {
        super("Кредитна картка заблокована");
    }
}