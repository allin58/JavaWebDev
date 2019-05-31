package thread9;

import java.util.concurrent.TimeUnit;

public class Consumer extends Thread{

    Store store;
    int product = 0;

    Consumer(Store store) {
        this.store =store;
    }

    @Override
    public void run() {
        try {
            while (product < 5) {
                product = product + store.get();
                System.out.println("потребитель купил " + product + " товаров");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток потребителя прерван");
        }


    }


}
