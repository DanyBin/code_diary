package com.impl;

import com.HelloService;
import org.apache.thrift.TException;

/**
 * @ClassName HelloServiceImpl
 * @Author bin
 * @Date 2020/11/9 下午5:29
 * @Decr TODO
 * @Link TODO
 **/
public class HelloServiceImpl implements HelloService.Iface {
    @Override
    public String sayHello(String userName) throws TException {
        return "hello " + userName + "!";
    }
}
