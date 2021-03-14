package concurrency.interrupt;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @ClassName NIOBlocked
 * @Author bin
 * @Date 2020/8/14 上午11:52
 * @Decr TODO
 * @Link TODO
 **/
public class NIOBlocked implements Runnable {
    private final SocketChannel sc;
    public NIOBlocked(SocketChannel sc) {this.sc = sc;}
    public void run() {
        System.out.println("waiting for read() in" + this);
        try {
            sc.read(ByteBuffer.allocate(1));
        } catch (IOException e) {
            throw new RuntimeException();
        }
        System.out.println("exit NIOBlocked ");
    }
}
