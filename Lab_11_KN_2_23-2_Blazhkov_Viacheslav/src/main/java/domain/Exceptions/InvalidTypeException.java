package domain.Exceptions;

public class InvalidTypeException extends RuntimeException {
    public InvalidTypeException() {
        super("Invalid transaction type");
    }
}
