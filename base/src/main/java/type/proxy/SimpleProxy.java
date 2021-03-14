package type.proxy;

/**
 * @ClassName SimpleProxy
 * @Author bin
 * @Date 2020/7/8 上午10:55
 * @Decr TODO
 * @Link TODO
 **/
public class SimpleProxy implements Interface {
    private Interface proxy;

    public SimpleProxy(Interface proxy){this.proxy = proxy;}

    public void doSomething() {
        System.out.println("SimpleProxy doSomething");
        proxy.doSomething();

    }

    public void doSomethingEles(String args) {
        System.out.println("SimpleProxy doSomethingElse" + args);
        proxy.doSomethingEles(args);
    }
}
