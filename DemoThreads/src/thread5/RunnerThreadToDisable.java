package thread5;

import java.util.concurrent.TimeUnit;

public class RunnerThreadToDisable {


    public static void main(String[] args) {
        System.out.println("Главный поток запущен");
        ThreadToDisable myThread = new ThreadToDisable();

        Thread myT = new Thread(myThread,"name of ThreadOfDisable");

        myT.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        myT.interrupt();





        System.out.println("Главный поток завершён");
    }
}
