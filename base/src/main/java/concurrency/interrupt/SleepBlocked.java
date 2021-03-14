package concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SleepBlocked
 * @Author bin
 * @Date 2020/8/14 上午11:09
 * @Decr TODO
 *          可中断任务
 * @Link TODO
 **/
public class SleepBlocked implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }
        System.out.println("exit SleepBlocked");
    }
}
