package com.zach.netty.thriftrpc;

import com.hzins.thrift.demo.HelloWorldService;
import org.apache.thrift.TException;

/**
 * Created by Administrator on 2016-9-15.
 */
public class HelloServiceImpl implements  HelloWorldService.Iface{
    @Override
    public String sayHello(String username) throws TException {
        System.out.println(username);
        return "ok";
    }
}
