package thread16;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class RunBlocking {

    public static void main(String[] args) {
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(2);

        new Thread() {
            public void run() {
                for (int i = 0; i < 4; i++) {

                    try {
                        queue.put("Java "+i);
                        System.out.println("Element " + i + " added");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
                     }.start();


        new Thread() {
            public void run() {
                for (int i = 0; i < 4; i++) {

                    try {
                        TimeUnit.MILLISECONDS.sleep(1000);
                        System.out.println("Element " + queue.take() + " took");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();


    }

}
