package concurrency.cooperation;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NotifyVsNotifyAll
 * @Author bin
 * @Date 2020/8/18 下午6:15
 * @Decr TODO
 * @Link TODO
 **/
public class NotifyVsNotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i < 5 ;i ++){
            exec.execute(new Task());
        }
        exec.execute(new Task2());

        //定时调度
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod = true;
            @Override
            public void run() {
                if(prod){
                    System.out.println("notify()");
                    Task.blocker.prod();
                    prod = false;
                } else {
                    System.out.println("notifyAll()");
                    Task.blocker.prodAll();
                    prod = true;
                }
            }
        },400,400);

        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        System.out.println("Timer canceled");
        TimeUnit.MILLISECONDS.sleep(500);

        Task2.blocker.prodAll();
        TimeUnit.MILLISECONDS.sleep(5000);
        System.out.println("shut down");
        exec.shutdownNow();
    }
}
