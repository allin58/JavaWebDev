package thread14;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Philosopher extends Thread {
    Semaphore sem;
    int num = 0;
    int id;
    Philosopher(Semaphore sem, int id) {
        this.sem = sem;
        this.id = id;
    }

    @Override
    public void run() {

        try {
            while (num < 3) {
                sem.acquire();
                System.out.println("Философ " + id + " садиться за стол");
                TimeUnit.MILLISECONDS.sleep(500);
                num++;
                System.out.println("Философ " + id + " выходит из-за стола");
                sem.release();
                TimeUnit.MILLISECONDS.sleep(500);
            }
        } catch (InterruptedException e) {
            System.out.println("у философа "+ id + " проблемы со здорорвьем");
        }


    }
}
