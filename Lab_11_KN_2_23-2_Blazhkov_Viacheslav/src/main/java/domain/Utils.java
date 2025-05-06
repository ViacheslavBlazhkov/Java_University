package domain;

import domain.Models.Bank;
import domain.Models.Client;
import domain.Models.TransactionDate;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
    static Bank bank;
    private static ArrayList<String> userNames = new
            ArrayList<String>(Arrays.asList("oastedbagelwithcreamcheese",
            "baeconandeggz", "FartinLutherKing", "coolshirtbra", "REVERANDTOAST", "kim_chi",
            "idrinkchocolatemilk",
            "chin_chillin", "ghostfacegangsta", "bigfootisreal", "santas_number1_elf",
            "thehornoftheunicorn"));

    public static Bank getBank() {
        if (bank != null)
            return bank;
        bank = new Bank();
        int i = 1;
        for (String userName : userNames) {
            long amount = 100 * i;
            Client user = bank.registryClient(userName, userName + "@hnu.com");
            user.depositMoney(amount, new TransactionDate(i, 2, 2022));
            i++;
        }
        return bank;
    }

}
