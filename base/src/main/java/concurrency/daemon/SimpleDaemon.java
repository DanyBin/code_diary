package concurrency.daemon;

import concurrency.SimplePriorities;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName SimpleDaemon
 * @Author bin
 * @Date 2020/8/11 上午11:41
 * @Decr TODO
 * @Link TODO
 **/
public class SimpleDaemon implements Runnable{
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
        for(int i=0; i< 10; i++){
            Thread deamon = new Thread(new SimpleDaemon());
            deamon.setDaemon(true);
            deamon.start();
        }
        System.out.println("over ");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
