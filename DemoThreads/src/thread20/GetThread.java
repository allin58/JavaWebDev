package thread20;

import java.util.concurrent.Exchanger;

public class GetThread  implements Runnable {
    Exchanger<String> exchanger;
    String message;

    public GetThread(Exchanger<String> exchanger) {
        message = "Привет мир!";
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        try {
            message += " - It is the message from GetThread (" + Thread.currentThread().getName() + ")";
            message = exchanger.exchange(message);
            System.out.println("PutThread " + Thread.currentThread().getName() + " получил: " + message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}