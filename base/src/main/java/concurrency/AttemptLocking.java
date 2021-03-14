package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName AttemptLocking
 * @Author bin
 * @Date 2020/8/11 下午7:19
 * @Decr TODO
 *      使用 Lock 对象
 * @Link TODO
 **/
public class AttemptLocking {
    private ReentrantLock lock = new ReentrantLock();
    public void untimed() {
        boolean captured = lock.tryLock();

        try {
            System.out.println("tryLock():" + captured);
        } catch (Exception e) {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public void timed() {
        boolean captured = false;

        try {
            captured = lock.tryLock(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException();
        }

        try {
            System.out.println("tryLock(Time):" + captured);
        } catch (Exception e) {
            if(captured) {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final AttemptLocking al  = new AttemptLocking();
        al.untimed();
        al.timed();

        Thread acquired = new Thread() {
            public void run() {
                    System.out.println("acquired");

            }
        };
        acquired.start();
        TimeUnit.SECONDS.sleep(4);
        //Thread.yield();
        al.untimed();
        al.timed();
    }

}

