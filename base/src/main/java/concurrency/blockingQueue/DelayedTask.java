package concurrency.blockingQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.NANOSECONDS;

/**
 * @ClassName DelayedTask
 * @Author bin
 * @Date 2020/8/20 上午10:19
 * @Decr TODO
 *      使用延迟队列
 * @Link TODO
 **/
public class DelayedTask implements Runnable,Delayed{

    private static int counter = 0;
    private final int id = counter ++;
    //延迟时间
    private final int delta;
    //触发器
    private final long trigger;


    protected static List<DelayedTask> squence = new ArrayList<DelayedTask>();

    public DelayedTask(int sec){
        delta = sec;
        trigger = System.nanoTime() + NANOSECONDS.convert(delta,MILLISECONDS);
    }



    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(),NANOSECONDS);
    }

    public int compareTo(Delayed o) {
        DelayedTask that = (DelayedTask)o;
        //没到触发时间
        if(trigger < ((DelayedTask) o).trigger) return -1;
        //超过触发时间
        if(trigger > ((DelayedTask) o).trigger) return 1;
        return 0;
    }

    public void run() {
        System.out.println(this + " ");
    }

    @Override
    public String toString() {
        return String.format("[%1$-4d]",delta) + " Task " + id;
    }

    public String summary(){
        return "(" + id + ":" + delta +")";
    }

    public static class EndSentinel extends DelayedTask {

        private ExecutorService exec;

        public EndSentinel(int sec,ExecutorService e) {
            super(sec);
            exec = e;
        }

        public void  run(){
            for(DelayedTask pt : squence) {
                System.out.println(pt.summary() + " ");
            }
            System.out.print(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}
