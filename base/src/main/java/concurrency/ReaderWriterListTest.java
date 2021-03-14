package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ReaderWriterListTest
 * @Author bin
 * @Date 2020/8/25 下午7:40
 * @Decr TODO
 *      读写锁的测试
 * @Link TODO
 **/
public class ReaderWriterListTest {
    ExecutorService exec = Executors.newCachedThreadPool();
    private final static int SIZE = 100;
    private static Random rand = new Random(47);
    private ReaderWriterList<Integer> list = new ReaderWriterList<Integer>(SIZE,0);

    private class Writer implements Runnable {

        public void run() {
            try {
                for(int i=0; i< 20; i++) {
                    list.set(i,rand.nextInt());
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("writer finished ");
            exec.shutdownNow();
        }
    }

    private class Reader implements Runnable {

        public void run() {
            try {
                while (!Thread.interrupted()) {
                    for(int i=0; i< SIZE; i++){
                        list.get(i);
                        TimeUnit.MILLISECONDS.sleep(1);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public ReaderWriterListTest(int reader,int writer) {
        for(int i=0;i < reader;i ++){
            exec.execute(new Reader());
        }

        for(int i=0;i < writer;i ++) {
            exec.execute(new Writer());
        }
    }
}
