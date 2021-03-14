package thredSynchronize;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CyclicBarrierDemo2
 * @Author bin
 * @Date 2020/9/24 下午3:15
 * @Decr TODO
 * @Link TODO
 **/
public class CyclicBarrierDemo2 {
    private static CyclicBarrier  cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        //线程A
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + " step3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        //线程B
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread() + " step1");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + " step2");
                    cyclicBarrier.await();

                    System.out.println(Thread.currentThread() + " step3");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        });

        executorService.shutdown();
    }
}
