package concurrency.daemon;

/**
 * @ClassName Daemon
 * @Author bin
 * @Date 2020/8/11 下午12:06
 * @Decr TODO
 *      在使用后台线程模型下，派生出来的子线程，默认都是后台线程
 * @Link TODO
 **/
public class Daemon implements Runnable {

    private Thread[] t = new Thread[10];
    public void run() {
        for(int i=0; i< t.length; i ++){
            t[i] = new Thread(new DaemonSpawn());
            t[i].start();
            System.out.println("DaemonSpawn " + i + " start,");
        }

        for(int i=0; i< t.length; i ++) {
            System.out.println("t["+i+"].isDaemon()=" + t[i].isDaemon() + ",");
        }
        while (true){
            Thread.yield();
        }
    }
}
