package com.zach.netty.thriftrpc;

import com.hzins.thrift.demo.HelloWorldService;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

/**m
 * Created by Administrator on 2016-9-15.
 */
public class ThriftServer {
    public static void startServer(int port) throws Exception {
        //需要的处理器 参数是泛型和具体的实现类
        TProcessor processor = new HelloWorldService.Processor<HelloWorldService.Iface>(new HelloServiceImpl());
        TServerSocket transport = new TServerSocket(port);
        TServer.Args args = new TServer.Args(transport);
        args.processor(processor);
        args.protocolFactory(new TBinaryProtocol.Factory());

        TServer server = new TSimpleServer(args);
        server.serve();
    }

    public static void main(String[] args) throws Exception {
        startServer(8080);
    }
}
