package concurrency.cooperation;

/**
 * @ClassName Blocker
 * @Author bin
 * @Date 2020/8/18 下午6:07
 * @Decr TODO
 * @Link TODO
 **/
public class Blocker {
    synchronized void waitingCall() {
        try {
            while (!Thread.interrupted()) {
                wait();
                System.out.print(Thread.currentThread() + " ");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void prod(){notify();}
    synchronized void prodAll(){notifyAll();}

}
