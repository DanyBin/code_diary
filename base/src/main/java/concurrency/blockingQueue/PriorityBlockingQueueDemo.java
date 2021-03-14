package concurrency.blockingQueue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName PriorityBlockingQueueDemo
 * @Author bin
 * @Date 2020/8/20 下午2:54
 * @Decr TODO
 * @Link TODO
 **/
public class PriorityBlockingQueueDemo {

    public static void main(String[] args) {
        Random random = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue = new PriorityBlockingQueue<Runnable>();

        exec.execute(new PrioritizedTaskProducer(queue,exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
}
