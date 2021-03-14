package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName SingleThradExector
 * @Author bin
 * @Date 2020/8/11 上午11:06
 * @Decr TODO
 * @Link TODO
 **/
public class SingleThradExector  {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newSingleThreadExecutor();
        for(int i=0; i< 5;i ++){
            exec.execute(new LiftOff());
        }
        exec.shutdown();
    }
}
