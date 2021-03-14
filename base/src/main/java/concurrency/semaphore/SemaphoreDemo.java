package concurrency.semaphore;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SemaphoreDemo
 * @Author bin
 * @Date 2020/8/21 下午7:30
 * @Decr TODO
 * @Link TODO
 **/
public class SemaphoreDemo {

    final static int SIZE = 25;

    public static void main(String[] args) throws InterruptedException {
        final Pool<Fat> pool = new Pool<Fat>(Fat.class,SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();

        for(int i=0; i< SIZE ; i ++){
            exec.execute(new CheckoutTask<Fat>(pool));
        }
        System.out.println("all CheckoutTask create");

        List<Fat> list = new ArrayList<Fat>();
        for(int i=0; i < SIZE ;i ++){
            Fat f = pool.checkOut();
            System.out.println(i + " main() thread checked out ");
            f.operation();
            list.add(f);
        }

        //持续在对象，直到对象的许可，被用光，会发生interrupt
        Future<?> future = exec.submit(new Runnable() {
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        future.cancel(true);
        System.out.println("checking in objects in " + list);
        for(Fat f : list) {
            pool.checkIn(f);
        }

        for(Fat f : list) {
            pool.checkIn(f);
        }
        exec.shutdown();
    }
}
