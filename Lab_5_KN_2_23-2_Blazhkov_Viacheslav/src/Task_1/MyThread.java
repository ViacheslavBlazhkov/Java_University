package Task_1;

import java.util.concurrent.Semaphore;

public class MyThread implements Runnable {
    private final int number;
    private final Semaphore semaphore;

    public MyThread(int number, Semaphore semaphore) {
        this.number = number;
        this.semaphore = semaphore;
    }

    public void run() {
        try {
            while (true) {
                semaphore.acquire();
                System.out.println("Thread " + number);
                semaphore.release();
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            return;
        }
    }
}
