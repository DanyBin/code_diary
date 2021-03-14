package concurrency.piped;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName PipedIO
 * @Author bin
 * @Date 2020/8/19 下午4:07
 * @Decr TODO
 * @Link TODO
 **/
public class PipedIO {
    public static void main(String[] args) throws IOException, InterruptedException {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
