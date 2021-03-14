package type.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName DynamicProxyHandler
 * @Author bin
 * @Date 2020/7/8 上午11:08
 * @Decr TODO
 * @Link TODO
 **/
public class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;
    public DynamicProxyHandler(Object proxied){this.proxied = proxied;}

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("----"+proxy.getClass() + " , method" + method + " ,args" + args);
        if(args !=null){
            for(Object arg: args){
                System.out.println(" " + arg);
            }
        }
        //注意使用的是代理对象- 转发给代理对象
        return method.invoke(proxied,args);
    }
}
