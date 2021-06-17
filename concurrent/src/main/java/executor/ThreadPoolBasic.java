package executor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName ThreadPoolBasic
 * @Author bin
 * @Date 2019/12/17 下午7:02
 * @Decr TODO
 *       创建基础的线程池使用
 *
 *       ThreadPoolExecutorDemo(int corePoolSize,       -- 表示线程池保有的最小线程数
                            int maximumPoolSize,    -- 表示线程池创建最大线程数
                            long keepAliveTime,     -- 表示空闲时间
                            TimeUnit unit,          -- keepAliveTime 的单位
                            BlockingQueue<Runnable> workQueue,  -- 工作队列
                            ThreadFactory threadFactory,        --  线程工厂
                            RejectedExecutionHandler handler)   --  自定义任务的拒绝策略
 * @Link TODO
 *
 * https://www.journaldev.com/1069/threadpoolexecutor-java-thread-pool-example-executorservice
 **/
public class ThreadPoolBasic {


    //工作线程
    class WorkThread implements Runnable{

        private String command;

        WorkThread(String command){
            this.command = command;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" Start. Command = "+command);
            processCommand();
            System.out.println(Thread.currentThread().getName()+" End.");
        }

        private void processCommand() {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public String toString(){
            return this.command;
        }
    }

    // 自定义任务的拒绝策略
    class RejectedExecutionHandlerImpl implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            //无法执行的命令
            System.out.println(r.toString() + " is rejected");
        }
    }

    // 监控线程池
    class monitorThread implements Runnable{

        private ThreadPoolExecutor executor;
        private int seconds;
        private boolean run=true;

        monitorThread(ThreadPoolExecutor executor, int delay){
            this.executor = executor;
            this.seconds = delay;
        }

        //关键线程池时
        public void shutdown(){
            this.run =false;
        }

        @Override
        public void run() {
            //打印线程池的情况
            while (run){
                System.out.println(
                        String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s"
                        ,this.executor.getPoolSize()
                        ,this.executor.getCorePoolSize()
                        ,this.executor.getActiveCount()
                        ,this.executor.getCompletedTaskCount()
                        ,this.executor.getTaskCount()
                        ,this.executor.isShutdown()
                        ,this.executor.isTerminated()));

                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    //获取线程工厂
    public static ThreadFactory defaultThreadFactory() {
        return new ThreadPoolBasic.DIYThreadFactory();
    }

    //自定义线程工厂
    static class DIYThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DIYThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "DIY-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }



    private static int corePoolSize = 2;
    private static int maximumPoolSize = 4;
    private static Long keepAliveTime = 10L;


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolBasic basic = new ThreadPoolBasic();
        //自定义任务处理策略
        RejectedExecutionHandlerImpl handler = basic.new RejectedExecutionHandlerImpl();

        //默认创建线程工厂
        ThreadFactory threadFactory = Executors.defaultThreadFactory();

        //自定义线程工厂
        ThreadFactory threadFactory1 = ThreadPoolBasic.defaultThreadFactory();


        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(11), threadFactory1, handler);


        //创建监控线程
        monitorThread monitorThread = basic.new monitorThread(threadPoolExecutor, 2);
        Thread thread = new Thread(monitorThread);
        thread.start();

        //提交任务到线程池中
        for(int i=0; i<100; i++){
            threadPoolExecutor.execute(basic.new WorkThread("cmd"+i));
        }

        Thread.sleep(30000);
        //shut down the pool
        threadPoolExecutor.shutdown();
        //shut down the monitor thread
        Thread.sleep(5000);
        monitorThread.shutdown();
    }
}

