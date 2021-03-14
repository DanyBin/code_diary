package concurrency.interrupt;

import java.io.IOException;
import java.io.InputStream;

/**
 * @ClassName IOBlocked
 * @Author bin
 * @Date 2020/8/14 上午11:11
 * @Decr TODO
 *      不可中断
 * @Link TODO
 **/
public class IOBlocked  implements Runnable{
    private InputStream in;
    public IOBlocked(InputStream in) {this.in = in;}
    public void run() {
        try {
            System.out.println("等待读取中");
            in.read();
        } catch (IOException e) {
            if(Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted from IOBlocked");
            } else {
                throw new RuntimeException();
            }
        }
        System.out.println("exit IOBlocked");
    }
}
