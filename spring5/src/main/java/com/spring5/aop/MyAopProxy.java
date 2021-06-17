package com.spring5.aop;

import org.springframework.aop.framework.AopProxy;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * @ClassName MyAopProxy
 * @Author bin
 * @Date 2021/4/1 下午1:07
 * @Decr TODO
 * @Link TODO
 **/
public class MyAopProxy implements AopProxy,InvocationHandler,Serializable {

    //目标对象
    private Object target;

    /**
     * 接收目标对象
     * 返回动态代理对象
     *
     * @return
     */
    public Object bind(Object target) {
        this.target = target;
        //调用getProxy
        return this.getProxy(MyAopProxy.class.getClassLoader()); // 这里就是个随便的类加载器 将我们动态创建的类加载到JVM
    }


    /**
     * 业务代码执行入口
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("代理前"+method.getName() + Arrays.toString(args));
        Object invoke = method.invoke(proxy, args);
        System.out.println("获取结果:" + invoke);
        return null;
    }

    @Override
    public Object getProxy() {
        return null;
    }

    @Override
    public Object getProxy(@Nullable ClassLoader classLoader) {
        return Proxy.newProxyInstance(classLoader,target.getClass().getInterfaces(),this);
    }
}
