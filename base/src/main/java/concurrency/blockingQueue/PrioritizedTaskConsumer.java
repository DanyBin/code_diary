package concurrency.blockingQueue;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * @ClassName PrioritizedTaskConsumer
 * @Author bin
 * @Date 2020/8/20 下午2:49
 * @Decr TODO
 * @Link TODO
 **/
public class PrioritizedTaskConsumer implements Runnable {

    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(PriorityBlockingQueue<Runnable> q){
        this.q = q;
    }

    public void run() {

        try {
            while (!Thread.interrupted()) {
                //使用当前线程运行task
                q.take().run();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Finished PrioritizedTaskConsumer ");
    }
}
