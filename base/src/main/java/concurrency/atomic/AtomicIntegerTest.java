package concurrency.atomic;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName AtomicIntegerTest
 * @Author bin
 * @Date 2020/8/13 上午11:20
 * @Decr TODO
 *      使用Atomic原子类
 * @Link TODO
 **/
public class AtomicIntegerTest implements Runnable {
    private AtomicInteger i = new AtomicInteger();
    public int getValue() {return i.get();}
    private void evenIncrement(){i.addAndGet(2);}

    public void run() {
        while (true) {
            evenIncrement();
        }
    }

    public static void main(String[] args) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.err.print("终止");
                System.exit(0);
            }
        },5000);


        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicIntegerTest atomicIntegerTest = new AtomicIntegerTest();

        exec.execute(atomicIntegerTest);
        while (true) {
            int val = atomicIntegerTest.getValue();
            if(val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }

    }
}
