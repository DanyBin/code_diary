package concurrency.example.bank;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BankTellerSimulation
 * @Author bin
 * @Date 2020/8/24 上午11:56
 * @Decr TODO
 * @Link TODO
 **/
public class BankTellerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int adjustment_period = 1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        //生成顾客
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));

        exec.execute(new TellerManager(exec,customers,adjustment_period));

        TimeUnit.SECONDS.sleep(10);
        exec.shutdownNow();
    }
}
