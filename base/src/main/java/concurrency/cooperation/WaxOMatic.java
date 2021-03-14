package concurrency.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName WaxOMatic
 * @Author bin
 * @Date 2020/8/18 下午5:45
 * @Decr TODO
 * @Link TODO
 **/
public class WaxOMatic {
    public static void main(String[] args) throws InterruptedException {
        Car car = new Car();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new WaxOff(car));
        exec.execute(new WaxOn(car));

        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
