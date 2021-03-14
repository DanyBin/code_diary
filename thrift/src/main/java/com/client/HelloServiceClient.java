package com.client;

import com.HelloService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * @ClassName HelloServiceClient
 * @Author bin
 * @Date 2020/11/9 下午5:31
 * @Decr TODO
 * @Link TODO
 **/
public class HelloServiceClient {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8888;
    public static final int TIMEOUT = 5000;
    private static TSocket tSocket;
    private static TTransport transport;

    public static void main(String[] args) {
        try {
            TTransport tTransport = getTTransport();
            TProtocol protocol = new TBinaryProtocol(tTransport);
            HelloService.Client client = new HelloService.Client(protocol);
            String result = client.sayHello("cc");
            System.out.println("thrift rpc result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TTransport getTTransport() throws Exception {
        try {
            tSocket = new TSocket(HOST, PORT, TIMEOUT);
            transport = new TFramedTransport(tSocket);
            if (!transport.isOpen()) {
                transport.open();
            }
            return transport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
