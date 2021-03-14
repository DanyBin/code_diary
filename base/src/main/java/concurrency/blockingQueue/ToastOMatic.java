package concurrency.blockingQueue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ToastOMatic
 * @Author bin
 * @Date 2020/8/19 下午3:52
 * @Decr TODO
 * @Link TODO
 **/
public class ToastOMatic {
    public static void main(String[] args) throws InterruptedException {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Butterer(dryQueue,butteredQueue));
        exec.execute(new Jammer(butteredQueue,finishedQueue));
        exec.execute(new Eater(finishedQueue));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
