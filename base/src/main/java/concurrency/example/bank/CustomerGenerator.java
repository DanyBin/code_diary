package concurrency.example.bank;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName CustomerGenerator
 * @Author bin
 * @Date 2020/8/24 上午10:44
 * @Decr TODO
 *      随机生成 顾客添加到 队列中
 * @Link TODO
 **/
public class CustomerGenerator implements Runnable{

    private CustomerLine customers;

    private static Random random = new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(random.nextInt(300));
                customers.put(new Customer(random.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator Interrupted");
        }
        System.out.println("CustomerGenerator terminating ");
    }
}
