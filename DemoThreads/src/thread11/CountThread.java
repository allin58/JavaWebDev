package thread11;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable {

    CommonResource res;
    ReentrantLock locker;

    public CountThread(CommonResource res, ReentrantLock locker) {
        this.res = res;
        this.locker = locker;
    }

    @Override
    public void run() {
       locker.lock();

        try {
            res.x = 1;

            for (int i = 0; i < 5; i++) {
                System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
                res.x++;
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            locker.unlock();
        }

    }
}
