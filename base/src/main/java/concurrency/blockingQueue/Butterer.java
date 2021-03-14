package concurrency.blockingQueue;

/**
 * @ClassName Butterer
 * @Author bin
 * @Date 2020/8/19 下午3:39
 * @Decr TODO
 * @Link TODO
 **/
public class Butterer implements Runnable {

    private ToastQueue dryQueue,butteredQueue;

    public Butterer(ToastQueue dry,ToastQueue butteredQueue){
        dryQueue = dry;
        this.butteredQueue = butteredQueue;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = dryQueue.take();
                t.butter();
                System.out.println(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            System.out.println("Butterer Interrupted");
        }
        System.out.println("Butterer off");
    }
}
