package concurrency.piped;

import java.io.IOException;
import java.io.PipedWriter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName Sender
 * @Author bin
 * @Date 2020/8/19 下午4:01
 * @Decr TODO
 * @Link TODO
 **/
public class Sender implements Runnable {

    private Random rand = new Random(47);
    private PipedWriter out = new PipedWriter();

    public PipedWriter getPipedWriter(){return out;}

    public void run() {
        try {
            while (true){
                for(char c = 'A'; c < 'z' ; c ++) {
                    out.write(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (IOException e) {
            System.out.println(e + "Sender writer execption");
        } catch (InterruptedException e) {
            System.out.println(e + "Sender sleep interrupted");
        }
    }
}
