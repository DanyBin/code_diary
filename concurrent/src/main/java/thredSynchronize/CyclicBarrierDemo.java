package thredSynchronize;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CyclicBarrierDemo
 * @Author bin
 * @Date 2019/12/16 下午4:49
 * @Decr TODO
 *      通过CyclicBarrier 实现线程同步
 * @Link TODO
 **/
public class CyclicBarrierDemo {

    static int x1=0,x2=0;


    //创建一个Cyc实例，添加一个所有子线程全部到达屏障后执行的任务
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2, new Runnable() {
        @Override
        public void run() {
            System.out.println(Thread.currentThread()  + " taks1 merger result");
        }
    });

    public static void main(String[] args){


        ExecutorService executorService = Executors.newFixedThreadPool(2);
        //添加线程A到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " task1-1");
                    System.out.println(Thread.currentThread() + " enter in barrier");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " task1-1 enter out barrier");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        //添加线程B到线程池
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " task1-2");
                    System.out.println(Thread.currentThread() + " enter in barrier");
                    cyclicBarrier.await();
                    System.out.println(Thread.currentThread() + " task1-2 enter out barrier");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });
        executorService.shutdown();



        ExecutorService executor = Executors.newFixedThreadPool(1);


        //创建同步类
        CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> {
            executor.execute(()->{
                System.out.println("x1="+x1+"   x2="+x2);
            });
        });

        for(int i=0; i< 1000; i ++ ){
            Thread t1 = new Thread(() -> {
                x1 ++;

                //线程同步
                try {
                    cyclicBarrier.await();
                }catch (Exception e){
                }

            });

            t1.start();

            Thread t2 = new Thread(() -> {
                x2++;

                //线程同步
                try {
                    cyclicBarrier.await();
                }catch (Exception e){

                }
            });

            t2.start();

        }
    }
}
