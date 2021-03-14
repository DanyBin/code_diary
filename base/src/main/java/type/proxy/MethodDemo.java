package type.proxy;

import java.lang.reflect.Proxy;

/**
 * @ClassName MethodDemo
 * @Author bin
 * @Date 2020/7/8 上午11:26
 * @Decr TODO
 * @Link TODO
 **/
public class MethodDemo {
    public static void main(String[] args) {
       MethodSelectorInterfact interfact = (MethodSelectorInterfact) Proxy.newProxyInstance(MethodSelectorInterfact.class.getClassLoader()
        ,new Class[]{MethodSelectorInterfact.class}
        ,new MethodSelector(new MethodSelectorIng()));

       interfact.boring();
       interfact.boring1();
       interfact.boring2();
    }
}
