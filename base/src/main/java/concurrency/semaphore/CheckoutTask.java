package concurrency.semaphore;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CheckoutTask
 * @Author bin
 * @Date 2020/8/21 下午7:27
 * @Decr TODO
 * @Link TODO
 **/
public class CheckoutTask<T> implements Runnable {
    private static int counter = 0;
    private final int id = counter ++ ;

    private Pool<T> pool;

    public CheckoutTask(Pool<T> pool) {
        this.pool = pool;
    }

    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this + " checkout " + item);
            TimeUnit.SECONDS.sleep(1);

            System.out.println(this + " checking in" + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "checkoutTask " + id + " ";
    }
}
