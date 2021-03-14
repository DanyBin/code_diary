package customSynTool;

import java.util.concurrent.FutureTask;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName AnaylzeAQSTool
 * @Author bin
 * @Date 2020/6/11 上午10:38
 * @Decr TODO
 *      简单分析concurrent中的阻塞类，基于AQS的实现
 * @Link TODO
 **/
public class AnaylzeAQSTool {


    ReentrantLock reentrantLock = new ReentrantLock();

    /**
     * Base of synchronization control for this lock. Subclassed
     * into fair and nonfair versions below. Uses AQS state to
     * represent the number of holds on the lock.
     */
    abstract static class ReenTrantSync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -5179523762034025860L;

        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();

        /**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         * 非公平的tryAcquire.
         */
        final boolean nonfairTryAcquire(int acquires) {
            //获取当前线程
            final Thread current = Thread.currentThread();
            //线程状态
            int c = getState();

            //c=0 ,锁未被持有
            if (c == 0) {
                //原子地更新状态，表示锁已经被占用
                if (compareAndSetState(0, acquires)) {
                    //设置 锁的拥有者
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            //如果当前线程 与 锁的拥有者相同，可重入
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) // overflow
                    throw new Error("Maximum lock count exceeded");
                setState(nextc);
                return true;
            }
            //否则失败
            return false;
        }

        protected final boolean tryRelease(int releases) {
            //获取当前状态
            int c = getState() - releases;
            //当前线程 != 锁持有的线程，则抛出异常
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();

            boolean free = false;
            //若当前状态 =0，则表示锁是打开的
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        protected final boolean isHeldExclusively() {
            // While we must in general read state before owner,
            // we don't need to do so to check if current thread is owner
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        // Methods relayed from outer class

        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }

        /**
         * Reconstitutes the instance from a stream (that is, deserializes it).
         */
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }


     Semaphore semaphore = new Semaphore(2);

    /**
     * Synchronization implementation for semaphore.  Uses AQS state
     * to represent permits. Subclassed into fair and nonfair
     * versions.
     */
    abstract static class SemaphoreSync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = 1192457210091910933L;

        //设置许可的数量
        SemaphoreSync(int permits) {
            setState(permits);
        }

        final int getPermits() {
            return getState();
        }


        final int nonfairTryAcquireShared(int acquires) {
            for (;;) {
                //计算可用的许可
                int available = getState();
                //剩余的许可
                int remaining = available - acquires;

                //若没有许可，则循环将终止
                //如果还有剩余的许可，原子方式降低许可的计数
                if (remaining < 0 ||
                        compareAndSetState(available, remaining))
                    return remaining;
            }
        }

        protected final boolean tryReleaseShared(int releases) {
            for (;;) {
                int current = getState();
                int next = current + releases;
                if (next < current) // overflow
                    throw new Error("Maximum permit count exceeded");
                if (compareAndSetState(current, next))
                    return true;
            }
        }

        final void reducePermits(int reductions) {
            for (;;) {
                int current = getState();
                int next = current - reductions;
                if (next > current) // underflow
                    throw new Error("Permit count underflow");
                if (compareAndSetState(current, next))
                    return;
            }
        }

        final int drainPermits() {
            for (;;) {
                int current = getState();
                if (current == 0 || compareAndSetState(current, 0))
                    return current;
            }
        }
    }

}
