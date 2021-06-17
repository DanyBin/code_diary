package executor.priority;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName Test
 * @Author bin
 * @Date 2021/4/6 下午4:00
 * @Decr TODO
 * @Link TODO
 **/
public class Test {

    public static void main(String[] args) {
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);

        ExecutorService executorService = new PThreadPoolExector(1, 3, 10, TimeUnit.MILLISECONDS, queue,
                new ThreadFactoryBuilder().setNameFormat(Test.class.getSimpleName()).build());

        for (int i = 0; i < 10; i++) {
            int i1 = new Random().nextInt(100);
            executorService.submit(new Task(i1) {
                @Override
                public void run() {
                    System.out.println(getValue());
                }
            });
        }
    }

     static class PThreadPoolExector extends ThreadPoolExecutor {

        public PThreadPoolExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public PThreadPoolExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public PThreadPoolExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public PThreadPoolExector(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }
    }
}
