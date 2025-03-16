package Task_3;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class Resource {
    public AtomicInteger sum = new AtomicInteger(0);
    int[] numbers = new int[15];

    public Resource() {
        Random rand = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = rand.nextInt(10);
        }
    }
}
