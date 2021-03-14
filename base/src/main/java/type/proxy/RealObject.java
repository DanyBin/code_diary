package type.proxy;

/**
 * @ClassName RealObject
 * @Author bin
 * @Date 2020/7/8 上午10:53
 * @Decr TODO
 * @Link TODO
 **/
public class RealObject implements Interface{
    public void doSomething() {
        System.out.println("doSomething");
    }
    public void doSomethingEles(String args) {
        System.out.println("somethingElse " + args);
    }
}
