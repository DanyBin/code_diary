package concurrency.countDwonLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName CountDownLatchDemo
 * @Author bin
 * @Date 2020/8/19 下午7:07
 * @Decr TODO
 * @Link TODO
 **/
public class CountDownLatchDemo {

    static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);

        for(int i=0; i< 10 ;i ++){
            exec.execute(new WaitingTask(latch));
        }

        for(int i=0;i < SIZE;i ++){
            exec.execute(new TaskPortion(latch));
        }

        System.out.println("launched all tasks");
        exec.shutdownNow();
    }
}
