package concurrency.share;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName EvenChecker
 * @Author bin
 * @Date 2020/8/11 下午3:47
 * @Decr TODO
 * @Link TODO
 **/
public class EvenChecker implements Runnable {

    private IntGenerator generator;
    private final int id;

    public EvenChecker(IntGenerator generator,int ident) {
        this.generator = generator;
        id = ident;
    }

    public void run() {
        while (!generator.isCanceled()) {
            int val = generator.next();
            if(val%2 != 0) {
                System.out.println(val + " 不是偶数");
                generator.cancel();
            }
        }
    }

    public static void test(IntGenerator generator,int count) {
        System.out.println("-----------");
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i=0; i< count ;i ++){
            exec.execute(new EvenChecker(generator,i));
        }
        exec.shutdown();
    }

    public static void test(IntGenerator generator){
        test(generator,10);
    }
}
