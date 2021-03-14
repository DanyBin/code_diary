package type.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName MethodSelector
 * @Author bin
 * @Date 2020/7/8 上午11:20
 * @Decr TODO
 * @Link TODO
 **/
public class MethodSelector implements InvocationHandler {

    private Object proxied;
    public MethodSelector(Object proxied){this.proxied = proxied;}
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(method.getName().equals("boring")){
            System.out.println("proxy the boring methond");
        }
        return method.invoke(proxied,args);
    }
}
