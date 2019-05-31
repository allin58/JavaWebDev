package thread1;

public class MyThread extends Thread {

    @Override
    public void run() {
        System.out.println("HelloWorld");
    }

    public static void main(String[] args) {

        System.out.println(Thread.currentThread());

        new MyThread().start();

    }
}
