package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName TestShutDown
 * @Author bin
 * @Date 2020/9/27 下午4:36
 * @Decr TODO
 * @Link TODO
 **/
public class TestShutDown {
    static void asynExecuteOne() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-- async execute one -- ");
            }
        });
        executorService.shutdown();
    }

    static void asynExecuteTwo() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println("-- async execute two -- ");
            }
        });
        executorService.shutdown();
    }

    public static void main(String[] args) {
        System.out.println("--- sync execute---");
        asynExecuteOne();
        asynExecuteTwo();
        System.out.println("--- execute over ---");
    }
}
