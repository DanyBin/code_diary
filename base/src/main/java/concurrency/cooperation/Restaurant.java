package concurrency.cooperation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName Restaurant
 * @Author bin
 * @Date 2020/8/18 下午8:00
 * @Decr TODO
 *   生产者与消费者 示例
 * @Link TODO
 **/
public class Restaurant {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPersion waitPersion = new WaitPersion(this);
    Chef chef = new Chef(this);

    public Restaurant(){
        exec.execute(chef);
        exec.execute(waitPersion);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
