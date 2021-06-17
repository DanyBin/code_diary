package io.nio.im;

import io.serialization.Data;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

public class IOClient {
  public static void main(String[] args) {
    new Thread(IOClient::run).start();
  }

  private static void run() {
    Socket socket = null;
    try {
      socket = new Socket("127.0.0.1", 8000);
    } catch (IOException e) {
      e.printStackTrace();
    }
    while (true) {
      try {
//        socket.getOutputStream().write(new Date() + ":hello world".getBytes());
        Thread.sleep(200);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
