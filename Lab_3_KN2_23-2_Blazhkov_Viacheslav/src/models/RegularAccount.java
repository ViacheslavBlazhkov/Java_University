package models;

import utils.Utils;

public class RegularAccount extends Account {
    public RegularAccount(long accountId) {
        super(accountId);
    }

    @Override
    public float getMonthlyInterest(int months) {
        float rate = 0.1F;
        long balance = getBalance();
        if (balance < 0) {
            return 0;
        }
        return Utils.calculateMonthlyInterest(months, rate, balance);
    }
}
