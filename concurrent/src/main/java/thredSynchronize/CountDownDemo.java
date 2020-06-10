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

    }
}
