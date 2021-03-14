package concurrency.interrupt;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Interrupting
 * @Author bin
 * @Date 2020/8/14 上午11:28
 * @Decr TODO
 * @Link TODO
 **/
public class Interrupting {
    private static ExecutorService exec = Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("Interrupted " + r.getClass().getName());
        f.cancel(true);
        System.out.println("Interrupted sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        System.exit(0);
    }
}
