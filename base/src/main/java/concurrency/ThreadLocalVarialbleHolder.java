package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ThreadLocalVarialbleHolder
 * @Author bin
 * @Date 2020/8/13 下午3:22
 * @Decr TODO
 * @Link TODO
 **/
public class ThreadLocalVarialbleHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        private Random rand = new Random(47);
        protected synchronized Integer initialValue(){
            return rand.nextInt(1000);
        }
    };

    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get() {return value.get();}

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0;i < 5;i ++){
            exec.execute(new ThreadLocalTest(i));
        }

        TimeUnit.SECONDS.sleep(3);
        exec.shutdown();
    }
}
