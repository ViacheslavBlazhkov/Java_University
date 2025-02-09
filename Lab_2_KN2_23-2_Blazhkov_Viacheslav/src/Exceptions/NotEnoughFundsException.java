package Exceptions;

public class NotEnoughFundsException extends Exception {
    public NotEnoughFundsException(String entity) {
        super(entity + " має недостатньо коштів");
    }
}