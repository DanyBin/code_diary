package concurrency.cooperation;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WaxOn
 * @Author bin
 * @Date 2020/8/18 下午5:46
 * @Decr TODO
 * @Link TODO
 **/
public class WaxOn implements Runnable {

    private Car car;
    public WaxOn(Car car) {this.car = car;}

    public void run() {
        try {
            while (!Thread.interrupted()) {
                System.out.println("wax on");
                TimeUnit.MILLISECONDS.sleep(200);
                car.waxed();
                car.waitForBuffing();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ending wax on ");
    }
}
