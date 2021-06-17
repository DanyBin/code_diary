package executor;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @ClassName MyThreadPool2
 * @Author bin
 * @Date 2021/3/22 下午6:49
 * @Decr TODO
 * @Link TODO
 **/
public class MyThreadPool2 {
    //线程池中默认线程的个数 5
    private static int WORK_NUM = 5;

    //队列默认任务个数为100，
    private static int TASK_COUNT = 100;

    //工程线程组
    private WorkThread[] workThreads;


    //任务队列，作为缓冲
    private final BlockingQueue<Runnable> taskQueue;

    //构造函数中，线程数
    private final int work_num ;

    public MyThreadPool2(int work_num,int taskCount) {
       if (work_num <= 0) work_num = WORK_NUM;
       if (taskCount <= 0) taskCount = TASK_COUNT;
       this.work_num = work_num;
       taskQueue = new ArrayBlockingQueue<Runnable>(taskCount);
       workThreads = new WorkThread[work_num];

       for (int i=0; i < work_num ; i ++) {
           workThreads[i] = new WorkThread();
           workThreads[i].start();
       }
       Runtime.getRuntime().availableProcessors();
    }

    //执行r任务
    public void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void destroy (){
        System.out.println("准备关闭线程池");
        for (int i=0;i < work_num; i ++) {
            workThreads[i].stopWork();
            workThreads[i] = null;
        }
        taskQueue.clear();
    }

    @Override
    public String toString() {
        return "线程池大小 :" + work_num  + " 等待执行任务个数:" + taskQueue.size();
    }
    private class WorkThread extends Thread {

        @Override
        public void run() {
            Runnable r = null;
            try {
                while (!isInterrupted()) {
                    r = taskQueue.take();
                    if (r != null) {
                        System.out.println(getId() + "准备执行" + r);
                        r.run();
                    }
                    r = null;
                }
            } catch (InterruptedException e) {

            }
        }

        public void stopWork(){
            interrupt();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 创建3个线程的线程池
        MyThreadPool2 t = new MyThreadPool2(3, 5);
        t.execute(new MyTask("testA"));
        t.execute(new MyTask("testB"));
        t.execute(new MyTask("testC"));
        t.execute(new MyTask("testD"));
        t.execute(new MyTask("testE"));
        System.out.println(t);
        Thread.sleep(10000);
        t.destroy();// 所有线程都执行完成 才destory
        System.out.println(t);
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
    }
    // 任务类
    static class MyTask implements Runnable {
        private String name;
        private Random r = new Random();
        public MyTask(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        @Override
        public void run() {// 执行任务
            try {
                Thread.sleep(r.nextInt(1000) + 2000); //随机休眠
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId() + " 被打断:"
                        + Thread.currentThread().isInterrupted());
            }
            System.out.println("任务 " + name + " 完成");
        }
    }
}
