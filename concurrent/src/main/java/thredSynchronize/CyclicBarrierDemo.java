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

    public static void main(String[] args){

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
