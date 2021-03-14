package concurrency.daemon;

import java.util.concurrent.ThreadFactory;

/**
 * @ClassName DaemonThreadFactory
 * @Author bin
 * @Date 2020/8/11 上午11:55
 * @Decr TODO
 *
 * @Link TODO
 **/
public class DaemonThreadFactory implements ThreadFactory {
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setDaemon(true);
        return t;
    }
}
