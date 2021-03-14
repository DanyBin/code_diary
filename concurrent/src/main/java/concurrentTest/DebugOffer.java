package concurrentTest;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @ClassName DebugOffer
 * @Author bin
 * @Date 2020/9/17 上午10:45
 * @Decr TODO
 * @Link TODO
 **/
public class DebugOffer {

    private  static ConcurrentLinkedQueue<Integer>  queue = new ConcurrentLinkedQueue();

    public static void main(String[] args) {
        for(int i = 0; i < 100 ; i ++) {
            int t = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.offer(t);
                }
            }).start();
        }
    }
}
