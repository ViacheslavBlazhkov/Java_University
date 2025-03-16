package Task_4;

import java.util.concurrent.Semaphore;

public class FactorialThread extends Thread {
    private Resource res;
    private Semaphore semaphore;
    private int startIndex;
    private int endIndex;

    public FactorialThread(Resource res, int startIndex, int endIndex, Semaphore semaphore) {
        this.res = res;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.semaphore = semaphore;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            for (int i = startIndex; i <= endIndex; i++) {
                int finalI = i;
                res.result.updateAndGet(x -> x * finalI);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
