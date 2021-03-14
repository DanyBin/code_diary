package concurrency.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName LockTest
 * @Author bin
 * @Date 2020/8/25 下午5:04
 * @Decr TODO
 * @Link TODO
 **/
public class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if(index > SIZE) index = 0;
        }finally {
            lock.unlock();
        }
    }

    public long read() {
        lock.lock();
        try {
            return value;
        }finally {
            lock.unlock();
        }
    }
}
