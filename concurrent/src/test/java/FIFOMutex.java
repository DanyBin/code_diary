import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

/**
 * @ClassName FIFOMutex
 * @Author bin
 * @Date 2020/9/3 上午10:30
 * @Decr TODO
 * @Link TODO
 **/
public class FIFOMutex {
    private final AtomicBoolean locked = new AtomicBoolean(false);
    private final Queue<Thread> waiters = new ConcurrentLinkedDeque<>();

    public void lock(){
        boolean wasInterrupted = false;
        Thread current = Thread.currentThread();
        waiters.add(current);

        //判断条件 1= 队首是否等于当前线程 2=当前locked是否被其它线程获取
        while (waiters.peek() != current || !locked.compareAndSet(false,true)) {
            //阻塞当前线程
            LockSupport.park(this);

            //忽略中断
            if(Thread.interrupted()) {
                wasInterrupted = true;
            }
        }

        //移除队首线程，释放
        waiters.remove();
        //重新设置中断标示
        if(wasInterrupted) {
            current.interrupt();
        }
    }

    public void unlock(){
        locked.set(false);
        LockSupport.unpark(waiters.peek());
    }
}
