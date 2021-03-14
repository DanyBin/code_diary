package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SimplePriorities
 * @Author bin
 * @Date 2020/8/11 上午11:17
 * @Decr TODO
 *      优先级的配置
 * @Link TODO
 **/
public class SimplePriorities implements Runnable {

    private int countDown = 5;
    private volatile double d;
    private int priority;

    public SimplePriorities(int priority){
        this.priority = priority;
    }

    @Override
    public String toString(){
        return Thread.currentThread() + " :" + countDown;
    }

    public void run() {
        Thread.currentThread().setPriority(priority);
        while (true) {
            for(int i=0; i< 100000; i ++){
                d += (Math.PI + Math.E) / (double)i;
                if(i %1000 == 0) {
                    Thread.yield();
                }
                System.out.println(this);
                if(--countDown == 0) return;
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i <5 ;i++){
            exec.execute(new SimplePriorities(Thread.MIN_PRIORITY));
        }
        exec.execute(new SimplePriorities(Thread.MAX_PRIORITY)); // Thread[pool-1-thread-6
        exec.shutdown();
    }
}
