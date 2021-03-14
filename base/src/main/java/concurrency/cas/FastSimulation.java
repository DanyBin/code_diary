package concurrency.cas;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName FastSimulation
 * @Author bin
 * @Date 2020/8/25 下午7:17
 * @Decr TODO
 *      乐观加锁机制，使用compareAndSet()的方法，在数据未锁定的状态下，进行数据更新
 *
 * @Link TODO
 **/
public class FastSimulation {
    //元素个数
    static final int N_ELEMENTS = 100000;
    //基因
    static final int N_GENES = 30;
    //进化
    static final int N_EVOLVERS = 50;

    static final AtomicInteger[][] GRID = new AtomicInteger[N_ELEMENTS][N_GENES];

    static Random rand = new Random(47);

    static class Evolver implements Runnable {

        public void run() {
            while (!Thread.interrupted()) {
                //随机一个元素
                int element = rand.nextInt(N_ELEMENTS);
                for(int i=0; i< N_GENES; i ++) {

                    int preivous = element - 1;
                    if(preivous < 0) preivous = N_ELEMENTS -1;

                    int next = element + 1;
                    if(next > N_ELEMENTS) next = 0;

                    int oldValue = GRID[element][i].get();

                    int newValue = oldValue + GRID[preivous][i].get() + GRID[next][i].get();

                    newValue /= 3;

                    if(!GRID[element][i].compareAndSet(oldValue,newValue)){
                        System.out.println("old Value changed from " + oldValue);
                    }
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        for (int i=0;i < N_ELEMENTS;i ++){
            for(int j=0; j< N_GENES; j++) {
                GRID[i][j] = new AtomicInteger(rand.nextInt(1000));
            }
        }

        for(int i=0;i < N_EVOLVERS;i++){
            exec.execute(new Evolver());
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
