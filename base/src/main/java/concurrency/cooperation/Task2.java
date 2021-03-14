package concurrency.cooperation;

/**
 * @ClassName Task2
 * @Author bin
 * @Date 2020/8/18 下午6:14
 * @Decr TODO
 * @Link TODO
 **/
public class Task2 implements Runnable {

    static Blocker blocker = new Blocker();
    public void run() {
        blocker.waitingCall();
    }
}
