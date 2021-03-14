package thredSynchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @ClassName SemaphoreDemo2
 * @Author bin
 * @Date 2020/9/25 上午10:52
 * @Decr TODO
 * @Link TODO
 **/
public class SemaphoreDemo2 {
    private static Semaphore  semaphore = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " A task over");
                semaphore.release();
            }
        });

        //将线程B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " A task over");
                semaphore.release();
            }
        });

        //等待子线程执行完毕，返回
        semaphore.acquire(2);
        System.out.println("task A is over");


        //将线程A添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " B task over");
                semaphore.release();
            }
        });

        //将线程B添加到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " B task over");
                semaphore.release();
            }
        });
        //等待子线程执行完毕，返回
        semaphore.acquire(2);
        System.out.println("task B is over");

        executorService.shutdown();


    }
}
