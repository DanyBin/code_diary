package concurrency.interrupt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CloseResource
 * @Author bin
 * @Date 2020/8/14 上午11:46
 * @Decr TODO
 *      关闭发生阻塞的底层资源
 * @Link TODO
 **/
public class CloseResource {

    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);

        InputStream in = new Socket("localhost",8080).getInputStream();

        exec.execute(new IOBlocked(in));
        exec.execute(new IOBlocked(System.in));

        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println("shutting down all threads");

        exec.shutdown();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("close InputStream");
        in.close();


        TimeUnit.SECONDS.sleep(1);
        System.out.println("close System");
        System.in.close();
    }
}
