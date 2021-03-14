package aqs;

import java.io.Serializable;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @ClassName NonReentrantLock
 * @Author bin
 * @Date 2020/9/14 下午12:04
 * @Decr TODO
 *      基于AQS 实现不可重入的独占锁
 *
 * @Link TODO
 **/
public class NonReentrantLock implements Lock,Serializable{


    //内部帮助类
    private static class Sync extends AbstractQueuedSynchronizer {

        //是否锁已经持有
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //如果state为0，则尝试获取锁
        @Override
        public boolean tryAcquire(int acquires){
            assert  acquires == 1;
            if(compareAndSetState(0,1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        //尝试释放锁，设置state为0
        @Override
        protected  boolean tryRelease(int release) {
            assert  release == 1;
            if(getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        //提供条件接口变量
        Condition newCondition () {
            return new ConditionObject();
        }
    }


    //创建一个Sync用来工作
    private final Sync sync = new Sync();


    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }


    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1,unit.toNanos(time));
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }


    final static NonReentrantLock lock = new NonReentrantLock();
    final static Condition notFull = lock.newCondition();
    final static Condition notEmpty = lock.newCondition();

    final static Queue<String> queue  = new LinkedBlockingDeque<>();
    final static int queueSize = 10;

    public static void main(String[] args) {
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取独占锁
                lock.lock();
                try {
                    //判断队列满了，则等待
                    while (queue.size() == queueSize) {
                        notEmpty.await();
                    }

                    //添加元素
                    queue.add("ele");

                    //唤醒消费线程
                    notFull.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });


        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    //队列空，则等待
                    while (0 == queue.size()) {
                        notFull.await();
                    }

                    //消费一个元素
                    String ele = queue.poll();
                    System.out.println(ele);
                    notEmpty.signalAll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        });

        producer.start();
        consumer.start();
    }
}
