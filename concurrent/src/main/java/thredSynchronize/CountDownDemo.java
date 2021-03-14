package thredSynchronize;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountDownDemo
 * @Author bin
 * @Date 2019/12/16 下午4:44
 * @Decr TODO
 *      使用CountDownLatch 类 实现线程同步
 * @Link TODO
 **/
public class CountDownDemo {



    private static volatile CountDownLatch c = new CountDownLatch(2);

    static int x1=0,x2=0;

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        for(int i=0;i < 1000;i ++){

            //线程同步工具类
            CountDownLatch countDownLatch = new CountDownLatch(1);

            executorService.execute(()->{
                x1++;
                countDownLatch.countDown(); //减1
            });

            executorService.execute(()->{
                x2++;
                countDownLatch.countDown();
            });

            countDownLatch.await();

            System.out.println("x1="+x1+"   x2="+x2);
        }


        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    c.countDown();
                }
                System.out.println("child threadOne over!");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    c.countDown();
                }
                System.out.println("child threadTwo over!");
            }
        });

        //启动子线程
        threadOne.start();
        threadTwo.start();
        System.out.println("wait child thread over!");

        //等待子线程执行完毕，返回
        c.await();
        System.out.println("all child thread over!");

    }
}
