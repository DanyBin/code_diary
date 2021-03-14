package concurrency.daemon;

/**
 * @ClassName DaemonDontRunFinally
 * @Author bin
 * @Date 2020/8/11 下午12:14
 * @Decr TODO
 * @Link TODO
 **/
public class DaemonDontRunFinally {
    public static void main(String[] args) {
        Thread t = new Thread(new ADaemon());
        t.setDaemon(true);
        t.start();
    }
}
