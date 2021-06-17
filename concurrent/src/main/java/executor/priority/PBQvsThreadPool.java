package executor.priority;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * @ClassName PBQvsThreadPool
 * @Author bin
 * @Date 2021/4/6 下午4:33
 * @Decr TODO
 * @Link TODO
 **/
public class PBQvsThreadPool {
    static class ComparableFutureTask<V> extends FutureTask<V> implements
            Runnable, Comparable<ComparableFutureTask<V>> {

        private int priority;

        public ComparableFutureTask(Callable<V> callable, int priority) {
            super(callable);
            this.priority = priority;
        }

        public ComparableFutureTask(Runnable runnable, V result, int priority) {
            super(runnable, result);
            this.priority = priority;
        }


        @Override
        public int compareTo(ComparableFutureTask<V> o) {
            return this.priority - o.priority;
        }

    }

    static class PBQThreadPoolExecutor extends ThreadPoolExecutor {
        public PBQThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     PriorityBlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public PBQThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     PriorityBlockingQueue<Runnable> workQueue,
                                     ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public PBQThreadPoolExecutor(int corePoolSize,
                                     int maximumPoolSize,
                                     long keepAliveTime,
                                     TimeUnit unit,
                                     PriorityBlockingQueue<Runnable> workQueue,
                                     RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }


        public <T> Future<T> submit(Runnable task, T result, int priority) {
            if (task == null)
                throw new NullPointerException();
            RunnableFuture<T> ftask = newTaskFor(task, result, priority);
            execute(ftask);
            return ftask;
        }

        public <T> Future<T> submit(Callable<T> task, int priority) {
            if (task == null)
                throw new NullPointerException();
            RunnableFuture<T> ftask = newTaskFor(task, priority);
            execute(ftask);
            return ftask;
        }

        protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T value,
                                                   int priority) {
            return new ComparableFutureTask<T>(runnable, value, priority);
        }

        protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable,
                                                   int priority) {
            return new ComparableFutureTask<T>(callable, priority);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        PriorityBlockingQueue<Runnable> pbq =
                new PriorityBlockingQueue<Runnable>(1024);

        PBQThreadPoolExecutor pool =
                new PBQThreadPoolExecutor(1024, 2048, 1000L, TimeUnit.MILLISECONDS, pbq);

        final int priority = 1;

        for(int i=0 ;i < 100;i ++) {
            int r = (int) (Math.random() * 100);
            pool.submit(new Task(i) {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread() + "->" +getValue());
                }
            },r);
        }
    }

}
