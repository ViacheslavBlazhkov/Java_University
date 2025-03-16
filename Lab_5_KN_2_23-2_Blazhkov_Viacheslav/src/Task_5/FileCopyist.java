package Task_5;

import java.io.File;
import java.util.concurrent.Semaphore;

public class FileCopyist {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1);
        File file1 = new File("file1.txt");
        File file2 = new File("file2.txt");

        FileThread thread1 = new FileThread(semaphore, file1, file2, 0, 3);
        FileThread thread2 = new FileThread(semaphore, file1, file2, 3, 6);
        FileThread thread3 = new FileThread(semaphore, file1, file2, 6, 9);

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
