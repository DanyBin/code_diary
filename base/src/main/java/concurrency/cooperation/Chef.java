package concurrency.cooperation;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName Chef
 * @Author bin
 * @Date 2020/8/18 下午8:03
 * @Decr TODO
 *      生产者与消费者 示例
 * @Link TODO
 **/
public class Chef  implements Runnable{
    private Restaurant restaurant;
    private int count = 0;

    public Chef(Restaurant restaurant) {this.restaurant = restaurant;}
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this){
                    while (restaurant.meal != null) {
                        wait();
                    }
                    if(++count == 10) {
                        System.out.println("out of food. closing");
                        restaurant.exec.shutdownNow();
                    }
                    System.out.print("Order up! ");
                    synchronized (restaurant.waitPersion) {
                        restaurant.meal = new Meal(count);
                        restaurant.waitPersion.notifyAll();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("chef interrupted");
        }
    }
}
