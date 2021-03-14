package concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Interrupting2
 * @Author bin
 * @Date 2020/8/14 下午4:01
 * @Decr TODO
 * @Link TODO
 **/
public class Interrupting2 {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("issuing t.interrupt");
        //中断阻塞的调用
        t.interrupt();
    }
}
