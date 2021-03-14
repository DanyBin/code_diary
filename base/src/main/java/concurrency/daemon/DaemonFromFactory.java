package concurrency.daemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName DaemonFromFactory
 * @Author bin
 * @Date 2020/8/11 上午11:56
 * @Decr TODO
 * @Link TODO
 **/
public class DaemonFromFactory implements Runnable {
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                System.out.println(Thread.currentThread() + " " +this);
            }
        } catch (InterruptedException e) {
            System.out.println("sleep() Interrupted");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for(int i=0; i< 10;i ++){
            exec.execute(new DaemonFromFactory());
        }
        System.out.println("begin");
        TimeUnit.MILLISECONDS.sleep(500);
    }
}
