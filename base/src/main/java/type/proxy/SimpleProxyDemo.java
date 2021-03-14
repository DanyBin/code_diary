package type.proxy;

/**
 * @ClassName SimpleProxyDemo
 * @Author bin
 * @Date 2020/7/8 上午10:58
 * @Decr TODO
 * @Link TODO
 **/
public class SimpleProxyDemo {
    public static void consumer(Interface interf){
        interf.doSomething();
        interf.doSomethingEles("ttt");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
