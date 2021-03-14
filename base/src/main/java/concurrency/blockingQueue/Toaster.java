package concurrency.blockingQueue;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Toaster
 * @Author bin
 * @Date 2020/8/19 下午3:35
 * @Decr TODO
 * @Link TODO
 **/
public class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {toastQueue = tq;}
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));

                Toast t = new Toast(count++);
                System.out.println(t);
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Toaster Interrupted");
        }
        System.out.println("Toaster off");
    }
}
