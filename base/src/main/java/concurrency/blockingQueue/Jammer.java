package concurrency.blockingQueue;

/**
 * @ClassName Jammer
 * @Author bin
 * @Date 2020/8/19 下午3:43
 * @Decr TODO
 * @Link TODO
 **/
public class Jammer implements Runnable {
    private ToastQueue butteredQueue,finishedQueue;

    public Jammer(ToastQueue buttered,ToastQueue finished) {
        butteredQueue = buttered;
        finishedQueue = finished;
    }
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = butteredQueue.take();
                t.jam();
                System.out.println(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Jammer Interrupted");
        }
        System.out.println("Jammer off");
    }
}
