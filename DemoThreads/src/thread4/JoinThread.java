package thread4;

public class JoinThread extends Thread {
    public JoinThread(String name) {
        super(name);
    }

    @Override
    public void run() {
       String nameT = getName();
       long timeout = 0;

        System.out.println("старт потока " + nameT);


        try {
            switch(nameT) {
                case "First" : timeout =5_000;
                break;
                case  "Second" : timeout = 1_000;
                break;
            }
            Thread.sleep(timeout);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("поток завершён " + nameT);

    }
}
