package concurrency.blockingQueue;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PrioritizedTaskProducer
 * @Author bin
 * @Date 2020/8/20 下午2:35
 * @Decr TODO
 * @Link TODO
 **/
public class PrioritizedTaskProducer implements Runnable {

    private Random random = new Random(47);
    private Queue<Runnable> queue;

    private ExecutorService exec;

    public PrioritizedTaskProducer(Queue<Runnable> q, ExecutorService e) {
        queue = q;
        exec = e;
    }

    public void run() {
        //生成任务
        for(int i=0; i < 20 ;i ++){
            queue.add(new PrioritizedTask(random.nextInt(10)));
            Thread.yield();
        }


        //加入最高级的任务
        try {
            for(int i=0;i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            //加入任务
            for(int i=0; i < 10 ;i ++){
                queue.add(new PrioritizedTask(i));
            }

            //添加哨兵，停止所有任务
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("Finished PrioritizedTaskProducer");
    }
}
