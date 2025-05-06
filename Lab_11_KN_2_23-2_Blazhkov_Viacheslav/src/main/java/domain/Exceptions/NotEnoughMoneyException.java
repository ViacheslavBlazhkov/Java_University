package domain.Exceptions;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException(long clientId) {
        super("Client " + clientId + " has not enough money");
    }
}
