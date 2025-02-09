package Exceptions;

public class IncorrectPinCodeException extends Exception {
    public IncorrectPinCodeException() {
        super("Невірний PIN-код");
    }
}