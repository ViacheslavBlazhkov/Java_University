package models;

import utils.Utils;

public class PlatinumAccount extends Account {
    public PlatinumAccount(long accountId) {
        super(accountId);
    }

    @Override
    public float getMonthlyInterest(int months) {
        float rate = 0.4F;
        long balance = getBalance();
        if (balance < 0) {
            return 0;
        }
        return Utils.calculateMonthlyInterest(months, rate, balance);
    }
}
