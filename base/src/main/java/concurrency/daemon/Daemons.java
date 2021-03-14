package concurrency.daemon;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Daemons
 * @Author bin
 * @Date 2020/8/11 下午12:09
 * @Decr TODO
 *      使用后台线程
 * @Link TODO
 **/
public class Daemons {
    public static void main(String[] args) throws InterruptedException {
        Thread d = new Thread(new Daemon());
        d.setDaemon(true);
        d.start();
        System.out.println(d.isDaemon() + " ------- ");
        TimeUnit.SECONDS.sleep(1);
    }
}
