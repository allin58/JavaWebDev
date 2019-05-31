package thread12;

public class Consumer implements Runnable{

    Store store;


    Consumer(Store store) {
        this.store =store;
    }

    public void run() {

        for (int i = 0; i < 6; i++) {

            store.get();

        }
    }


}
