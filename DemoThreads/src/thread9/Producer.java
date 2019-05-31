package thread9;

import java.util.concurrent.TimeUnit;

public class Producer extends Thread {
    Store store;
    int product = 5;

    Producer(Store store) {
        this.store =store;
    }

    @Override
    public void run() {
        try {
            while (product > 0) {
                product = product - store.put();
                System.out.println("производителю осталось произвести " + product + " товаров");
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Поток производителя прерван");
        }


    }
}
