package test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName TestTimer
 * @Author bin
 * @Date 2020/9/27 下午12:09
 * @Decr TODO
 * @Link TODO
 **/
public class TestTimer {
    static Timer timer = new Timer();

    public static void main(String[] args) {

        //添加任务1，延迟500ms执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("---one Task---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error ");
            }
        },500);


        //添加任务1，延迟500ms执行
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("---two Task---");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("error ");
            }
        },1000);

    }
}
