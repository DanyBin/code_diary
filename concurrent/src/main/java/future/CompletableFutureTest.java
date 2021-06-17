package future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static org.junit.Assert.assertTrue;

/**
 * @ClassName CompletableFutureTest
 * @Author bin
 * @Date 2021/3/31 上午10:45
 * @Decr TODO
 *      基于CompletableFuture 的Demo.
 *      实现非阻塞的异步调用
 * @Link TODO
 **/
public class CompletableFutureTest {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFutureTest test = new CompletableFutureTest();
        System.out.println(test.calculateAsyc().get());
    }
    /**
     * 手动创建
     * @return
     */
    public Future<String> calculateAsyc() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newFixedThreadPool(2).submit(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            completableFuture.complete("over");
        });

        return completableFuture;
    }

    /**
     * 批量调用
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void pathAsyc () throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        combinedFuture.get();// 完成条件是future1, future2和future3都完成

        assertTrue(future1.isDone());
        assertTrue(future2.isDone());
        assertTrue(future3.isDone());
    }

    /**
     * 异常处理
     */
    public void err() {
        String name = null;
        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s,t) -> s != null ? s : "err");

    }
}
