package Task_3;

import java.util.Arrays;
import java.util.concurrent.Semaphore;

public class SumCalcWithSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        Resource res = new Resource();

        CalcThread thread1 = new CalcThread(res, 0, 3, semaphore);
        CalcThread thread2 = new CalcThread(res, 3, 6, semaphore);
        CalcThread thread3 = new CalcThread(res, 6, 9, semaphore);
        CalcThread thread4 = new CalcThread(res, 9, 12, semaphore);
        CalcThread thread5 = new CalcThread(res, 12, 15, semaphore);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
            thread5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.toString(res.numbers));
        System.out.println("Sum = " + res.sum);
    }
}

