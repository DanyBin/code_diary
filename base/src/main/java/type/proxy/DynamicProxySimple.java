package type.proxy;

import java.lang.reflect.Proxy;

/**
 * @ClassName SimpleDynamicProxy
 * @Author bin
 * @Date 2020/7/8 上午11:14
 * @Decr TODO
 * @Link TODO
 **/
public class SimpleDynamicProxy {
    public static void consumer(Interface iface){
        iface.doSomething();
        iface.doSomethingEles("sss");
    }

    public static void main(String[] args) {
        RealObject realObject = new RealObject();
        consumer(realObject);


        //创建动态代理
       Interface proxyInstance = (Interface)Proxy.newProxyInstance(Interface.class.getClassLoader() //类加载器
               , new Class[]{Interface.class}    //接口列表
               , new DynamicProxyHandler(realObject));  //接口的实现
       consumer(proxyInstance);
    }
}
