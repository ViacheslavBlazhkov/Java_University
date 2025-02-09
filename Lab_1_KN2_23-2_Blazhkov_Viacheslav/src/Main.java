import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        printHello();
        mathOperations();
        printArray();
    }

    public static void printHello() {
        System.out.print("Введіть ім'я: ");
        String name = scanner.nextLine();
        System.out.print("Введіть вік: ");
        String age = scanner.nextLine();
        System.out.printf("Привіт, %s, вам %s років\n\n", name, age);
    }

    public static void mathOperations() {
        System.out.print("Введіть перше число: ");
        int number1 = Integer.parseInt(scanner.nextLine());
        System.out.print("Введіть друге число: ");
        double number2 = Double.parseDouble(scanner.nextLine());

        System.out.printf("Додавання %d + %.2f = %.2f\n", number1, number2, number1 + number2);
        System.out.printf("Віднімання %d - %.2f = %.2f\n", number1, number2, number1 - number2);
        System.out.printf("Множення %d * %.2f = %.2f\n", number1, number2, number1 * number2);
        if (number2 == 0) {
            System.out.print("На 0 не ділимо!");
        } else {
            System.out.printf("Ділення %d / %.2f = %.2f\n\n", number1, number2, number1 / number2);
        }
    }

    public static void printArray() {
        System.out.print("Введіть кількість чисел: ");
        int count = Integer.parseInt(scanner.nextLine());
        int[] array = new int[count];
        for (int i = 0; i < array.length; i++) {
            System.out.printf("Число %d: ", i + 1);
            array[i] = Integer.parseInt(scanner.nextLine());
        }
        int sum = Arrays.stream(array).sum();
        double avg = (double) sum / array.length;
        System.out.printf("Сума: %d\n", sum);
        System.out.printf("Середнє: %.2f\n", avg);
    }
}