package concurrency.daemon;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DaemonThreadPoolExecutor
 * @Author bin
 * @Date 2020/8/11 下午12:00
 * @Decr TODO
 * @Link TODO
 **/
public class DaemonThreadPoolExecutor extends ThreadPoolExecutor {

    public DaemonThreadPoolExecutor() {
        super(0,Integer.MAX_VALUE,60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),
                new DaemonThreadFactory());
    }
}
