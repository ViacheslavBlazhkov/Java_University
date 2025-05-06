package domain.Exceptions;

import java.util.List;

public class InvalidDepositMoneyRequestException extends RuntimeException {
    public InvalidDepositMoneyRequestException(List<String> errors) {
        super("Errors: " + String.join(", ", errors));
    }
}
