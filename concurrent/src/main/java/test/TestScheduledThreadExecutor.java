package test;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName TestScheduledThreadExecutor
 * @Author bin
 * @Date 2020/9/27 下午2:18
 * @Decr TODO
 * @Link TODO
 **/
public class TestScheduledThreadExecutor {
    static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

    public static void main(String[] args) {
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("---one Task---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException();
            }
        },500, TimeUnit.MILLISECONDS);


        executor.schedule(new Runnable() {
            @Override
            public void run() {


                for(int i = 0 ; i < 2 ; ++i) {
                    System.out.println("---two Task---");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },1000, TimeUnit.MILLISECONDS);

        executor.shutdown();
    }
}
