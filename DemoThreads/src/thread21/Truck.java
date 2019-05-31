package thread21;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

public class Truck implements Runnable {
    private Phaser phaser;
    private String number;
    private int capacity;
    private Storage storafeFrom;
    private Storage storageTo;
    private Queue<Item> bodyStorage;
    public Truck(Phaser phaser, String name, int capacity, Storage stFrom,
                 Storage stTo) {
        this.phaser = phaser;
        this.number = name;
        this.capacity = capacity;
        this.bodyStorage = new ArrayDeque<Item>(capacity);
        this.storafeFrom = stFrom;
        this.storageTo = stTo;
        this.phaser.register();
    }
    public void run() {
        loadTruck();
        phaser.arriveAndAwaitAdvance();
        goTruck();
        phaser.arriveAndAwaitAdvance();
        unloadTruck();
        phaser.arriveAndDeregister();
    }
    private void loadTruck() {
        for (int i = 0; i < capacity; i++) {
            Item g = storafeFrom.getGood();
            if (g == null) { // если в хранилище больше нет товара,
// загрузка грузовика прерывается

                return;

            }
            bodyStorage.add(g);
            System.out.println("Грузовик " + number + " загрузил товар No"
                    + g.getRegistrationNumber());
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    private void unloadTruck() {
        int size = bodyStorage.size();
        for (int i = 0; i < size; i++) {
            Item g = bodyStorage.poll();
            storageTo.setGood(g);
            System.out.println("Грузовик " + number + " разгрузил товар No"
                    + g.getRegistrationNumber());
            try {
                TimeUnit.MILLISECONDS.sleep(50);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    private void goTruck() {
        try {
            Thread.sleep(new Random(100).nextInt(500));
            System.out.println("Грузовик " + number + " начал поездку.");

            TimeUnit.MILLISECONDS.sleep((new Random(100).nextInt(1000)));
            System.out.println("Грузовик " + number + " завершил поездку.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
