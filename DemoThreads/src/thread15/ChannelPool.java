package thread15;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ChannelPool <T> {
    private final static  int POOL_SIZE = 5;
    private  final Semaphore semaphore = new Semaphore(POOL_SIZE,true);
    private final Queue<T> resources = new LinkedList<>();

    public ChannelPool(Queue<T> source) {
        resources.addAll(source);
    }

    public T getRsource(long maxWaitMillis) throws ResourceException {

        try {
            if (semaphore.tryAcquire(maxWaitMillis,TimeUnit.MILLISECONDS)) {
              // System.out.println("a");
                T res = resources.poll();
                return res;
            }
        } catch (InterruptedException e) {
          //  System.out.println("b");
            throw new ResourceException(e);
        }
      //  System.out.println("c");
        throw new ResourceException(": превышено время ожидания");
    }


    public void returnResource(T res) {
        resources.add(res);
        semaphore.release();
    }


}
