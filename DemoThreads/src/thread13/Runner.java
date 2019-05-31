package thread13;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class Runner {

    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        CommonResource res = new CommonResource();
        new Thread(new CountThread(res,sem,"CountThread 1")).start();
        new Thread(new CountThread(res,sem,"CountThread 2")).start();
        new Thread(new CountThread(res,sem,"CountThread 3")).start();


    }
}
