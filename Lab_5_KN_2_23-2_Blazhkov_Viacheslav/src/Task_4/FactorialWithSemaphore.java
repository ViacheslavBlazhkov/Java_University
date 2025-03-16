package Task_4;

import java.util.concurrent.Semaphore;

public class FactorialWithSemaphore {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        Resource res = new Resource();

        FactorialThread thread1 = new FactorialThread(res, 1, 2, semaphore);
        FactorialThread thread2 = new FactorialThread(res, 3, 4, semaphore);
        FactorialThread thread3 = new FactorialThread(res, 5, 6, semaphore);
        FactorialThread thread4 = new FactorialThread(res, 7, 8, semaphore);
        FactorialThread thread5 = new FactorialThread(res, 9, 10, semaphore);

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

        System.out.println("Factorial = " + res.result);
    }
}

