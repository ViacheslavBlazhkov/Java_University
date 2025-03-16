package Task_3;

import java.util.concurrent.Semaphore;

public class CalcThread extends Thread {
    private Resource res;
    private Semaphore semaphore;
    private int startIndex;
    private int endIndex;

    public CalcThread(Resource res, int startIndex, int endIndex, Semaphore semaphore) {
        this.res = res;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = startIndex; i < endIndex; i++) {
                res.sum.addAndGet(res.numbers[i]);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
