package models;

import utils.Utils;

public class GoldAccount extends Account {
    public GoldAccount(long accountId) {
        super(accountId);
    }

    @Override
    public float getMonthlyInterest(int months) {
        float rate = 0.2F;
        long balance = this.getBalance();
        if (balance < 0) {
            return 0;
        }
        return Utils.calculateMonthlyInterest(months, rate, balance);
    }
}
