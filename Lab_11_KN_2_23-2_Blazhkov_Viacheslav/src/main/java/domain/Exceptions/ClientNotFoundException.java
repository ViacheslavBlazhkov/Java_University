package domain.Exceptions;

public class ClientNotFoundException extends RuntimeException {
    public ClientNotFoundException(long clientId) {
        super("Could not find client " + clientId);
    }
}
