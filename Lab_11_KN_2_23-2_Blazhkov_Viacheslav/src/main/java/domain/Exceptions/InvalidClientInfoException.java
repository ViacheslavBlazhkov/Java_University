package domain.Exceptions;

public class InvalidClientInfoException extends RuntimeException {
    public InvalidClientInfoException(Iterable<String> errors) {
        super("Invalid client info: " + String.join(", ", errors));
    }
}
