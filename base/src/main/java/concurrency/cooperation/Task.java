package concurrency.cooperation;

/**
 * @ClassName Task
 * @Author bin
 * @Date 2020/8/18 下午6:13
 * @Decr TODO
 * @Link TODO
 **/
public class Task implements Runnable {
    static Blocker blocker = new Blocker();
    public void run() {
        blocker.waitingCall();
    }
}
