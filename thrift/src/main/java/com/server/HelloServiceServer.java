package com.server;

import com.HelloService;
import com.impl.HelloServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * @ClassName HelloServiceServer
 * @Author bin
 * @Date 2020/11/9 下午5:30
 * @Decr TODO
 * @Link TODO
 **/
public class HelloServiceServer {
    public static final int PORT = 8888;

    public static void main(String[] args) {
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(PORT);
            HelloService.Processor processor = new HelloService.Processor(new HelloServiceImpl());
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TBinaryProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));
            TServer server = new TNonblockingServer(arg);
            server.serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
