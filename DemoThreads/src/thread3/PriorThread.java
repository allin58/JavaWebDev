package thread3;

import java.util.concurrent.TimeUnit;

public class PriorThread extends Thread {

    public PriorThread(String name) {
        super(name);
    }


    @Override
    public void run() {
        for (int i = 0; i < 50 ; i++) {
            System.out.println(getName() + " " + i);
        }

        try {
              //sleep(10);

            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
           System.err.print(e);
        }

    }
}
