package domain.Exceptions;

import java.util.List;

public class InvalidTransferMoneyRequestException extends RuntimeException {
    public InvalidTransferMoneyRequestException(List<String> errors) {
        super("Errors: " + String.join(", ", errors));
    }
}
