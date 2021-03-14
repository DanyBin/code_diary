package test;

import java.util.concurrent.*;

/**
 * @ClassName TestFuture
 * @Author bin
 * @Date 2020/9/27 下午4:58
 * @Decr TODO
 * @Link TODO
 **/
public class TestFuture {
    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(1,1,1L, TimeUnit.MINUTES
            ,new ArrayBlockingQueue<Runnable>(1)
    ,new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Future futureOne = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runable one");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Future futureTwo = executor.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("start runable two");
            }
        });

        Future futureThree = null;
        try {
            futureThree = executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println("start runable three");
                }
            });
        }catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        System.out.println("task one " + futureOne.get());
        System.out.println("task two " + futureTwo.get());
        System.out.println("task three " + (futureThree == null ? null:futureThree.get()));

        executor.shutdown();
    }
}
