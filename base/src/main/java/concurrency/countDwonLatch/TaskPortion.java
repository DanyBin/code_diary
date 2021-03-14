package concurrency.countDwonLatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TaskPortion
 * @Author bin
 * @Date 2020/8/19 下午7:00
 * @Decr TODO
 * @Link TODO
 **/
public class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter ++ ;
    private static Random rand = new Random(47);

    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        System.out.println(this +  " completed");
    }

    @Override
    public String toString() {
        return String.format("%1$-3d",id);
    }
}
