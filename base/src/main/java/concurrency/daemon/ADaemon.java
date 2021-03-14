package concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName ADaemon
 * @Author bin
 * @Date 2020/8/11 下午12:13
 * @Decr TODO
 *      在使用后台模型下进行关闭时，不会运行finally
 * @Link TODO
 **/
public class ADaemon implements Runnable {
    public void run() {
        try {
            System.out.println("start ADaemon");
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            System.out.println("一直在运行？");
        }

    }
}
