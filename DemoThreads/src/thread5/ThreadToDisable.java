package thread5;

import java.util.concurrent.TimeUnit;

public class ThreadToDisable implements Runnable {
   private boolean isActive;

   void disable(){
       isActive = false;
   }

    public ThreadToDisable() {
        isActive = true;
    }

    @Override
    public void run() {
        System.out.printf("Поток %s начал работу... \n", Thread.currentThread().getName());
        int counter=1;

       while(!Thread.currentThread().isInterrupted()) {



            System.out.println("Цикл " + counter++);

            try {

               TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
               // Thread.currentThread().interrupt();
                break;
            }

            System.out.printf("Поток %s завершил работу... \n", Thread.currentThread().getName());

        }




    }
}
