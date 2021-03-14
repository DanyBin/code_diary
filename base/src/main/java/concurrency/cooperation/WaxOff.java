package concurrency.cooperation;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName WaxOff
 * @Author bin
 * @Date 2020/8/18 下午5:43
 * @Decr TODO
 * @Link TODO
 **/
public class WaxOff implements Runnable {

    private Car car;

    public WaxOff(Car c) {this.car = c;}
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitForWaxing();
                System.out.println("Wax off");
                TimeUnit.MILLISECONDS.sleep(200);
                car.buffed();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("ending");
    }
}
