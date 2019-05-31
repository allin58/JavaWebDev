package thread14;

import java.util.concurrent.Semaphore;

public class PhilosopherApp {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        for (int i = 0; i < 6; i++) {
            new Philosopher(sem,i).start();

        }

    }
}
