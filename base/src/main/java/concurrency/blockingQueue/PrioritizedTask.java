package concurrency.blockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PrioritizedTask
 * @Author bin
 * @Date 2020/8/20 上午10:53
 * @Decr TODO
 *          使用优先级队列
 *          具有可阻塞的读取操作
 * @Link TODO
 **/
public class PrioritizedTask implements Runnable,Comparable<PrioritizedTask> {

    private Random random = new Random(47);
    private static int counter = 0;

    private final int id = counter ++ ;

    private final int priority;

    protected static List<PrioritizedTask> sequence = new ArrayList<PrioritizedTask>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }


    public int compareTo(PrioritizedTask o) {
        return priority < o.priority ? 1 : (priority > o.priority ? -1 :0);
    }

    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return String.format("[%1$-3d]",priority) + " Task " + id;
    }

    public String summary(){
        return "(" + id + ":" + priority + ")";
    }


    public static class EndSentinel extends PrioritizedTask {

        private ExecutorService exec;
        public EndSentinel(ExecutorService exec) {
            super(-1);
            this.exec = exec;
        }

        public void run(){
            int count = 0;
            for(PrioritizedTask pt :sequence) {
                System.out.println(pt.summary());
                if(++count % 5 == 0) {
                    System.out.println();
                }
            }
            System.out.println(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }

}
