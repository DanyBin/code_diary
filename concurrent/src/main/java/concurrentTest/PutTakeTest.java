package concurrentTest;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName PutTakeTest
 * @Author bin
 * @Date 2020/6/5 下午4:41
 * @Decr TODO
 * @Link TODO
 **/
public class PutTakeTest {

    private static final ExecutorService pool = Executors.newCachedThreadPool();
    private final AtomicInteger putSum = new AtomicInteger(0);
    private final AtomicInteger takeSum = new AtomicInteger(0);

    private final CyclicBarrier barrier;
    private final BoundedBuffer<Integer> buffer;
    private final int nTrials,nParis;


    private BarrierTimer timer;

    public PutTakeTest(int capacity,int nParis,int nTrials){
        this.buffer = new BoundedBuffer<>(capacity);
        this.nTrials =nTrials;
        this.nParis = nParis;
        this.timer = new BarrierTimer();
        this.barrier = new CyclicBarrier(nParis*2+1, this.timer);
    }


    public static void main(String[] args) {
        new PutTakeTest(10,10,100).test();
        pool.shutdown();
    }

    void test(){
        try{
            for(int i=0; i<nParis;i++){
                pool.submit(new Producer());
                pool.submit(new Consumer());
            }
            barrier.await();//等待所有的线程就绪
            barrier.await();//等待所有的线程执行完成
            System.out.println(putSum.get()+ "  "+takeSum.get());
            long nsPerItem = timer.getTime() / (nParis * nTrials);
            System.out.println("throughput: "+ nsPerItem);
            Assert.assertEquals(putSum.get(),takeSum.get());
        }catch (Exception e){
            throw new RuntimeException();
        }
    }

    class Producer implements Runnable{

        @Override
        public void run() {
            try {
                int seed = (this.hashCode() ^ (int)System.nanoTime());
                int sum =0;
                barrier.await();

                for(int i=nTrials;i > 0; --i){
                    buffer.put(seed);
                    sum += seed;
                    seed = xorShirft(seed);
                }
                putSum.getAndAdd(sum);
                barrier.await();
            }catch (Exception e){
                throw new RuntimeException();
            }
        }
    }

    class Consumer implements Runnable{
        @Override
        public void run() {
            try {
                barrier.await();
                int sum =0;
                for(int i = nTrials;i > 0; --i){
                    sum += buffer.take();
                }
                takeSum.getAndAdd(sum);
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
    }

    /**
     * 使用一个栏栅动作来测量启动与结束时间
     */
    class BarrierTimer implements Runnable{
        private boolean started;
        private long startTime, endTime;

        @Override
        public synchronized void run() {
            long t = System.nanoTime();
            if(!started){
                started = true;
                startTime = t;
            }else {
                endTime = t;
            }
        }
        public synchronized void clear(){
            started = false;
        }

        public synchronized Long getTime(){
            return endTime - startTime;
        }
    }

    static int xorShirft(int y){
        y ^= (y << 6);
        y ^= (y >> 21);
        y ^= (y <<7);
        return y;
    }
}
