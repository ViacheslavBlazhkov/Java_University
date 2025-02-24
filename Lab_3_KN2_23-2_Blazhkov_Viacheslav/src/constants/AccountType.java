package constants;

import jdk.jshell.spi.ExecutionControl;

public class AccountType {
    public static String REGULAR = "regular";
    public static String GOLD = "gold";
    public static String PLATINUM = "platinum";

    public static String getAccountType(int acType) throws ExecutionControl.NotImplementedException {
        switch (acType) {
            case 1:
                return REGULAR;
            case 2:
                return GOLD;
            case 3:
                return PLATINUM;
            default:
                throw new ExecutionControl.NotImplementedException("");
        }
    }
}
