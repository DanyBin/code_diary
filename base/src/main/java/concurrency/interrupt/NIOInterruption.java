package concurrency.interrupt;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName NIOInterruption
 * @Author bin
 * @Date 2020/8/14 上午11:54
 * @Decr TODO
 * @Link TODO
 **/
public class NIOInterruption {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(8080);
        InetSocketAddress isa = new InetSocketAddress("localhost",8080);

        SocketChannel sc1 = SocketChannel.open(isa);
        SocketChannel sc2 = SocketChannel.open(isa);

        Future<?> f = exec.submit(new NIOBlocked(sc1));

        exec.execute(new NIOBlocked(sc2));
        exec.shutdown();
        TimeUnit.SECONDS.sleep(1);

        //中断
        f.cancel(true);

        TimeUnit.SECONDS.sleep(1);
        sc2.close();

    }
}
