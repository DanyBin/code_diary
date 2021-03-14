package jvm.oom;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CGLibOOM
 * @Author bin
 * @Date 2020/9/30 上午10:27
 * @Decr TODO
 *  使用CGLib使得方法区出现内存溢出异常
 *  VM Args: -XX:PermSize=5M -MM:MaxPermSize=5M -- jdk1.8 已经被移除
 *   -XX:MaxMetaspaceSize=5M -> 指定元空间最大值
 *
 * @Link TODO
 **/
public class CGLibOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(o,objects);
                }
            });
        }
    }
    static class OOMObject{}
}
