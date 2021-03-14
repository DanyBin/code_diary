package concurrency;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ADaemon
 * @Author bin
 * @Date 2020/8/11 下午12:13
 * @Decr TODO
 * @Link TODO
 **/
public class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("start ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
