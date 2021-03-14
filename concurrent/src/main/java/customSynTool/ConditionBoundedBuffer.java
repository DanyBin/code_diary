package customSynTool;

import javax.annotation.concurrent.ThreadSafe;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName ConditionBoundedBuffer
 * @Author bin
 * @Date 2020/6/10 下午8:19
 * @Decr TODO
 *      使用显示的lock 与Condition 来实现缓存
 * @Link TODO
 **/
@ThreadSafe
public class ConditionBoundedBuffer<T> {

    final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty =lock.newCondition();

    private final T[] items = (T[]) new Object[10];

    private int tail, head,count;

    public void put(T x) throws InterruptedException{
        lock.lock();
        try {
            while (count == items.length){
                notFull.wait();
            }
            items[tail] = x;
            if(++tail == items.length){
                tail = 0;
            }
            ++count;
            notEmpty.signal();
        }finally {
            lock.unlock();
        }
    }

    public T take() throws  InterruptedException{
        lock.lock();
        try {
            while (count == 0){
                notEmpty.wait();
            }
            T item = items[head];
            if(++head == items.length){
                head = 0;
            }
            --count;
            notFull.signal();
            return item;
        }finally {
            lock.unlock();
        }
    }
}
