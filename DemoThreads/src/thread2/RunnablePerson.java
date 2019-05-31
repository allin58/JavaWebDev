package thread2;

public class RunnablePerson extends Person implements Runnable {


    public RunnablePerson(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(getName() + " HelloWorld");
        }
    }

    public static void main(String[] args) {
        System.out.println("start");
        RunnablePerson p1 = new RunnablePerson("Alisa");
        Thread t1 = new Thread(p1);
        t1.start();

        RunnablePerson p2 = new RunnablePerson("Bob");
        Thread t2 = new Thread(p2);
        t2.start();

        System.out.println("end");
    }
}
