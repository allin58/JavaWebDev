package thread11;
import java.util.concurrent.locks.ReentrantLock;


public class Runner {

    public static void main(String[] args) {
        CommonResource commonResource = new CommonResource();
        ReentrantLock locker = new ReentrantLock();
        for (int i = 0; i < 6; i++) {
            Thread t = new Thread(new CountThread(commonResource, locker));
            t.setName("Поток " + i);
            t.start();
        }
    }
}
