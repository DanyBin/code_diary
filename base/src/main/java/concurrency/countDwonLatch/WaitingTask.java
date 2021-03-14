package concurrency.countDwonLatch;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName WaitingTask
 * @Author bin
 * @Date 2020/8/19 下午7:04
 * @Decr TODO
 * @Link TODO
 **/
public class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id  = counter ++ ;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch){
        this.latch = latch;
    }
    public void run() {
        try {
            latch.await();
            System.out.println("latch barrier passed for " + this);
        } catch (InterruptedException e) {
            System.out.println(this + " interrypted");
        }
    }

    @Override
    public String toString() {
        return String.format("WaitingTask %1$-3d",id);
    }
}
