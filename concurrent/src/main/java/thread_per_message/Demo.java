package thread_per_message;


import com.sun.xml.internal.ws.api.pipe.Fiber;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @ClassName Demo
 * @Author bin
 * @Date 2020/2/24 上午11:21
 * @Decr TODO
 *         thread-per-message 模式。 主要解决并发中的分工问题。 如何高效地拆任务并分配线程。
 *
 * @Link TODO
 **/
public class Demo {
    public static void main(String[] args) throws IOException{
        ServerSocketChannel bind = ServerSocketChannel.open().bind(new InetSocketAddress(8080));


        //处理请求
        while (true){
            //获取
            SocketChannel sc = bind.accept();
            //每个请求创建一个线程，进行处理
            new Thread(new Runnable() {
                @Override
                public void run() {

                }
            });
        }
    }
}
