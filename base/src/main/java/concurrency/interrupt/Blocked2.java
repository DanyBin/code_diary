package concurrency.interrupt;

/**
 * @ClassName Blocked2
 * @Author bin
 * @Date 2020/8/14 下午3:59
 * @Decr TODO
 * @Link TODO
 **/
public class Blocked2 implements Runnable {
    BlockedMutex blockedMutex = new BlockedMutex();
    public void run() {

        //调用BlockedMutex中的方法
        System.out.println("waiting for f() in BlockedMutex");
        blockedMutex.f();
        System.out.println("Broken out of blocked call");
    }
}
