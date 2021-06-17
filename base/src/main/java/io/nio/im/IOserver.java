package io.nio.im;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOserver {
  public static void main(String[] args) throws IOException {
    //监听端口
    ServerSocket serverSocket = new ServerSocket(8000);

    new Thread(() -> {
      while (true) {
        //阻塞方案获取新的连接
        Socket socket = null;
        try {
          socket = serverSocket.accept();
        } catch (IOException e) {
          e.printStackTrace();
        }

        //每一个新的连接都创建一个现成，负责读取数据
        Socket finalSocket = socket;
        new Thread(() -> {
          try {
            int len;
            byte[] data = new byte[1024];
            InputStream inputStream = finalSocket.getInputStream();

            //按字节流的方式读取数据
            while ((len = inputStream.read(data)) != -1) {
              System.out.println(new String(data,0,len));
            }
          }catch (IOException e) {

          }
        }).start();
      }
    }).start();
  }
}
