package concurrency.exchanger;

import concurrency.semaphore.Fat;
import generics.BasicGenerator;

import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName ExchangerDemo
 * @Author bin
 * @Date 2020/8/21 下午8:29
 * @Decr TODO
 *      创建对象的同时，将对象的List集合，通过Exchanger 传递给消费者，进行消费。提供效率
 *
 * @Link TODO
 **/
public class ExchangerDemo {

    static int size = 10;
    static int delay = 5;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();

        //创建单一的Exchanger，
        Exchanger<List<Fat>> exchanger = new Exchanger<List<Fat>>();

        //两个用户交换的List
        List<Fat>
                producerList = new CopyOnWriteArrayList<Fat>(),
                consumerList = new CopyOnWriteArrayList<Fat>();

        exec.execute(new ExchangerProducer<Fat>(exchanger, BasicGenerator.create(Fat.class),producerList));
        exec.execute(new ExchangerConsumer<Fat>(exchanger,consumerList));

        TimeUnit.SECONDS.sleep(delay);
        exec.shutdownNow();
    }
}
