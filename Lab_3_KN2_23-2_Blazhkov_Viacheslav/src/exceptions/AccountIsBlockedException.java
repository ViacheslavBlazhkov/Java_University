package exceptions;

public class AccountIsBlockedException extends Exception {

    public AccountIsBlockedException() {
        super("Account is blocked!");
    }
}
