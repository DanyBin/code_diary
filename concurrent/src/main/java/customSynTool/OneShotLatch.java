package customSynTool;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName OneShotLatch
 * @Author bin
 * @Date 2020/6/11 上午10:20
 * @Decr TODO
 *      二元闭锁操作
 *      关闭(0)、打开（1）
 *
 * @Link TODO
 **/
@ThreadSafe
public class OneShotLatch {

    private final Sync sync = new Sync();

    //通知，打开闭锁
    public void signal(){ sync.releaseShared(0);}

    //阻塞
    public void await() throws InterruptedException{
        sync.acquireSharedInterruptibly(0);
    }

    private class Sync extends AbstractQueuedSynchronizer{
        @Override
        protected int tryAcquireShared(int arg) {
            //如果闭锁是开的(state = 1),则操作成功，否则将失败
            return (getState() == 1) ? 1:-1;
        }

        @Override
        protected boolean tryReleaseShared(int arg) {
            setState(1); //打开闭锁
            return true;//其他线程可以获取该闭锁
        }
    }
}
