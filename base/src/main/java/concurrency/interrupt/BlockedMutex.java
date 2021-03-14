package concurrency.interrupt;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BlockedMutex
 * @Author bin
 * @Date 2020/8/14 下午3:46
 * @Decr TODO
 *
 * @Link TODO
 **/
public class BlockedMutex {
    private Lock lock = new ReentrantLock();
    public BlockedMutex(){
        //加锁，
        lock.lock();
    }

    public void f(){
        try {
            lock.lockInterruptibly();
            System.out.println("lock acquired in f()");
        } catch (InterruptedException e) {
            System.out.println("InterruptedException");
        }

    }
}
