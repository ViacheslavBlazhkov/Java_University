package Task_1;

import java.util.concurrent.Semaphore;

public class ThreeThreadsWithSemaphore {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        Thread thread1 = new Thread(new MyThread(1, semaphore));
        Thread thread2 = new Thread(new MyThread(2, semaphore));
        Thread thread3 = new Thread(new MyThread(3, semaphore));

        thread1.start();
        thread2.start();
        thread3.start();

        while (true) {
            try {
                System.in.read();
                thread1.interrupt();
                thread2.interrupt();
                thread3.interrupt();
                break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
