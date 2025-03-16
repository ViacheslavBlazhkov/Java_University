package Task_5;

import java.io.*;
import java.util.concurrent.Semaphore;

public class FileThread extends Thread {
    private Semaphore semaphore;
    private File file1;
    private File file2;
    private int startIndex;
    private int endIndex;

    public FileThread(Semaphore semaphore, File file1, File file2, int startIndex, int endIndex) {
        this.semaphore = semaphore;
        this.file1 = file1;
        this.file2 = file2;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file1));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file2));

            char[] buffer = new char[1024];
            int numCharsRead;

            StringBuilder result = new StringBuilder();
            while ((numCharsRead = bufferedReader.read(buffer, startIndex, endIndex)) != -1) {
                result.append(new String(buffer, startIndex, numCharsRead));
            }
            bufferedWriter.append(result.toString());

            bufferedReader.close();
            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            semaphore.release();
        }
    }
}
