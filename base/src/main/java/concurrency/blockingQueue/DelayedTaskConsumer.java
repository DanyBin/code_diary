package concurrency.blockingQueue;

import java.util.concurrent.DelayQueue;

/**
 * @ClassName DelayedTaskConsumer
 * @Author bin
 * @Date 2020/8/20 上午10:34
 * @Decr TODO
 *          延迟任务的消费者
 * @Link TODO
 **/
public class DelayedTaskConsumer implements Runnable {

    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                q.take().run();
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
        System.out.println("Finished DelayedTaskConsumer ");
    }
}
