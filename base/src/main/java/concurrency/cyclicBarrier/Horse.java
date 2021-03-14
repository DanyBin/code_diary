package concurrency.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @ClassName Horse
 * @Author bin
 * @Date 2020/8/19 下午7:22
 * @Decr TODO
 * @Link TODO
 **/
public class Horse implements Runnable{
    private static int counter = 0;

    //每个赛马对应的id
    private final int id = counter ++ ;
    private int striedes = 0;
    private static Random random = new Random(47);

    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b){
        barrier= b;
    }

    public synchronized int getStrides () { return striedes;}

    public void run() {
        try {
            //赛马不断的前进
            while (!Thread.interrupted()) {
                synchronized (this) {
                    striedes += random.nextInt(3);
                }
                barrier.await();
            }
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public String toString() {
        return "Horse " + id + " ";
    }

    //每个马都打印自己
    public String tracks() {
        StringBuilder s = new StringBuilder();
        for(int i=0;i < getStrides();i ++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}
