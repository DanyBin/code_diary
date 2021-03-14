package concurrency.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Blocked3
 * @Author bin
 * @Date 2020/8/14 下午4:37
 * @Decr TODO
 * @Link TODO
 **/
public class Blocked3 implements Runnable {

    private volatile double d = 0.0;

    public void run() {


        while (!Thread.currentThread().isInterrupted()) {
            NeedsCleanup n1 = new NeedsCleanup(1);
            try {
                System.out.println("sleeping");
                TimeUnit.SECONDS.sleep(1);

                NeedsCleanup n2 = new NeedsCleanup(2);

                try {
                    System.out.println("Calculating");
                    for (int i = 0; i < 2500000; i++) {
                        d = d + (Math.PI + Math.E) / d;
                    }
                    System.out.println("finished ");
                } finally {
                    n2.cleanup();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                n1.cleanup();
            }
        }

        System.out.println("exiting ");

    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Blocked3());
        t.start();
        TimeUnit.MILLISECONDS.sleep(100);
        t.interrupt();
    }
}
