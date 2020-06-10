package executor;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @ClassName ThreadPoolDemo
 * @Author bin
 * @Date 2019/12/17 下午5:04
 * @Decr TODO
 *      自定义 - 线程池实现
 *      采用生产者 与消费者的方式实现。
 *      构建思路:
 *          1. 创建一个阻塞队列 和一组工作线程。
 *          2. 通过executor()方法 来提交Runnable任务，并将任务加入到workQueue中
 *          3. WorkerThread内容的工作线程会「消费」workQueue中的任务并执行任务。
 *
 * @Link TODO
 **/
public class ThreadPoolDemo {


    //采用阻塞队列-- 生产者
    BlockingDeque<Runnable> workQueue;

    //保存-工作线程
    List<WorkerThread> threads = new ArrayList<>();



    ThreadPoolDemo(int poolSize,BlockingDeque<Runnable>  workQueue){
        this.workQueue = workQueue;

        //创建工作线程
        for(int i=0;i < poolSize;i++){
            WorkerThread workerThread = new WorkerThread();
            workerThread.start();
            threads.add(workerThread);
        }
    }

    //提交任务
    void execute(Runnable command) throws InterruptedException{
        workQueue.put(command);
    }

    //工作线程-负责消费任务，并执行任务
    class WorkerThread extends Thread {
        @Override
        public void run()  {

            //循环获取任务并执行
           while (true){
               try {
                   Runnable take = workQueue.take();
                   take.run();
               } catch (InterruptedException e) {
                   e.printStackTrace();
                   Thread.setDefaultUncaughtExceptionHandler(new UEHLogger());
               }
           }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        BlockingDeque<Runnable> workQueue = new LinkedBlockingDeque<>(2);

        //创建线程池
        ThreadPoolDemo threadPoolDemo = new ThreadPoolDemo(10, workQueue);
        for(int i=0;i< 100;i++){
            threadPoolDemo.execute(()->{
                System.out.println(Thread.currentThread().getId() + "   hello");
            });
        }
    }

}

