import Exceptions.CreditCardIsBlockedException;
import Exceptions.IncorrectPinCodeException;
import Models.ATM;
import Models.Bank;
import Models.CreditCard;
import Repositories.CreditCardRepository;

import java.util.*;

import static java.lang.System.exit;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static CreditCardRepository creditCardRepository = CreditCardRepository.getInstance();
    static Bank bank = new Bank(50000, new ArrayList<>());
    static ATM atm = new ATM(bank, 30000);

    public static void main(String[] args) {
        while (true) {
            try {
                printMenu();
                int actionId = Integer.parseInt(scanner.nextLine());
                System.out.println();
                processAction(actionId);
            } catch (NumberFormatException e) {
               System.out.println("Невірний формат вводу");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\nДоступні дії:");
        System.out.println("\t1. Вийти з програми");
        System.out.println("\t2. Переглянути баланс усіх карток");
        System.out.println("\t3. Створити картку");
        System.out.println("\t4. Внести кошту на картку");
        System.out.println("\t5. Переглянути баланс банку");
        System.out.println("\t6. Видалити картку");
        System.out.println("\t7. Переглянути баланс картки");
        System.out.println("\t---------------------------------");
        System.out.println("\t8. Зняти кошти з картки");
        System.out.println("\t9. Переглянути  картки із балансом");
        System.out.println("\t10. Оновити баланс картки");
        System.out.println("\t---------------------------------");
        System.out.println("\t11. Заблокувати картку");
        System.out.println("\t12. Переказ між картками");
        System.out.println("\t13. Переглянути заблоковані картки");
        System.out.println("\t14. Розблокувати картку\n");
        System.out.print("\tВведіть номер дії: ");
    }

    private static void processAction(int actionId) {
        switch (actionId) {
            case 1:
                exit(0);
                break;
            case 2: {
                List<CreditCard> cards = creditCardRepository.getCardsWithAmount(0);
                if (cards.isEmpty()) {
                    System.out.println("Карток немає");
                    break;
                }
                System.out.println("Картка\t\t\t | Баланс");
                for (CreditCard card : cards) {
                    System.out.println(card.getCardNumber() + " | " + card.getBalance() + " EUR");
                }
                break;
            }
            case 3: {
                String name = getFromConsole("Введіть ім'я: ");
                String pinCode = getFromConsole("Введіть PIN-код: ");
                CreditCard card = bank.createCreditCard(name, pinCode);
                System.out.println("Кредитна картка успішно створена. Номер: " + card.getCardNumber());
                break;
            }
            case 4: {
                try {
                    String cardNumber = getFromConsole("Введіть номер карти: ");
                    if( ! CreditCard.isCardNumberValid(cardNumber)) {
                        throw new Exception("Невірний формат номеру карти");
                    }
                    CreditCard card = creditCardRepository.findCardByNumber(cardNumber);
                    double amount = Double.parseDouble(getFromConsole("Введіть суму: "));
                    atm.refillCard(card, amount);
                    System.out.println("Картка успішно поповнена. Баланс: " + card.getBalance() + " EUR");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 5: {
                System.out.println("Баланс банку: " + bank.getTotalBalance());
                break;
            }
            case 6: {
                try {
                    String cardNumber = getFromConsole("Введіть номер карти: ");
                    if( ! CreditCard.isCardNumberValid(cardNumber)) {
                        throw new Exception("Невірний формат номеру карти");
                    }
                    String pinCode = getFromConsole("Введіть PIN-код: ");
                    creditCardRepository.removeCardByNumber(cardNumber, pinCode);
                    System.out.println("Картка успішно видалена");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 7: {
                try {
                    CreditCard card = checkCard();
                    System.out.println("Баланс карти: " + card.getBalance() + " EUR");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 8:
                try {
                    CreditCard card = checkCard();
                    double amount = Double.parseDouble(getFromConsole("Введіть суму: "));
                    atm.withdraw(card, amount);
                    System.out.println("Кошти знято. Баланс карти: " + Math.round(card.getBalance() * 100.0) / 100.0 + " EUR");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 9: {
                double amount = Double.parseDouble(getFromConsole("Введіть суму: "));
                List<CreditCard> cards = creditCardRepository.getCardsWithAmount(amount);
                if (cards.isEmpty()) {
                    System.out.println("Карток немає");
                    break;
                }
                for (CreditCard card : cards) {
                    System.out.println(card.getCardNumber() + " | " + card.getBalance() + " EUR");
                }
                break;
            }
            case 10: {
                try {
                    String cardNumber = getFromConsole("Введіть номер карти: ");
                    if( ! CreditCard.isCardNumberValid(cardNumber)) {
                        throw new Exception("Невірний формат номеру карти");
                    }
                    CreditCard card = creditCardRepository.findCardByNumber(cardNumber);
                    double amount = Double.parseDouble(getFromConsole("Введіть суму: "));
                    bank.updateCardBalance(card, amount);
                    System.out.println("Баланс карти оновлено. Баланс: " + card.getBalance() + " EUR");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 11: {
                try {
                    CreditCard card = checkCard();
                    card.block(true);
                    System.out.println("Картку заблоковано");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 12: {
                try {
                    System.out.println("Ваша картка:");
                    CreditCard cardFrom = checkCard();
                    if (cardFrom.isBlocked()) {
                        throw new CreditCardIsBlockedException();
                    }
                    System.out.println("Картка отримувача:");
                    String cardNumber = getFromConsole("Введіть номер карти: ");
                    if( ! CreditCard.isCardNumberValid(cardNumber)) {
                        throw new Exception("Невірний формат номеру карти");
                    }
                    CreditCard cardTo = creditCardRepository.findCardByNumber(cardNumber);
                    if (cardTo.isBlocked()) {
                        throw new CreditCardIsBlockedException();
                    }
                    double amount = Double.parseDouble(getFromConsole("Введіть суму: "));
                    cardFrom.transfer(amount, cardTo);
                    System.out.println("Переказ успішний");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case 13: {
                List<CreditCard> cards = creditCardRepository.getBlockedCards();
                if (cards.isEmpty()) {
                    System.out.println("Карток немає");
                    break;
                }
                for (CreditCard card : cards) {
                    System.out.println(card.getCardNumber());
                }
                break;
            }
            case 14: {
                try {
                    CreditCard card = checkCard();
                    card.block(false);
                    System.out.println("Картку розблоковано");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
        }
    }

    private static CreditCard checkCard() throws Exception {
        String cardNumber = getFromConsole("Введіть номер карти: ");
        if( ! CreditCard.isCardNumberValid(cardNumber)) {
            throw new Exception("Невірний формат номеру карти");
        }
        String pinCode = getFromConsole("Введіть PIN-код: ");
        CreditCard card = creditCardRepository.findCardByNumber(cardNumber);
        if (!card.checkPinCode(pinCode)) {
            throw new IncorrectPinCodeException();
        }
        return card;
    }

    private static String getFromConsole(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}