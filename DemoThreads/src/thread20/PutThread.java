package thread20;

import java.util.concurrent.Exchanger;

public class PutThread implements Runnable {
    Exchanger<String> exchanger;
    String message;

    public PutThread(Exchanger<String> exchanger) {
        message = "Hello Java!";
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            message += " - It is the message from PutThread (" + Thread.currentThread().getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("PutThread " + Thread.currentThread().getName() + " получил: " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
