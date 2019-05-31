package thread13;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class CountThread implements Runnable {

    CommonResource res;
    Semaphore sem;
    String name;


    public CountThread(CommonResource res, Semaphore sem, String name) {
        this.res = res;
        this.sem = sem;
        this.name = name;
    }

    @Override
    public void run() {

        try {
            System.out.println(name + " ожидается разрешение");
            sem.acquire();
            res.x = 1;
            for (int i = 0; i < 5; i++) {
                System.out.println(this.name + ":" + res.x);
                res.x++;
                TimeUnit.MILLISECONDS.sleep(100);
                   }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(name + " освобождает разрешение");
        sem.release();


    }
}
