package concurrency.exchanger;

import generics.Generator;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangerProducer
 * @Author bin
 * @Date 2020/8/21 下午8:20
 * @Decr TODO
 *      生产者对象
 *
 * @Link TODO
 **/
public class ExchangerProducer<T> implements Runnable {

    private Generator<T> generator;
    //栅栏。 List<T> 作为交互对象
    private Exchanger<List<T>> exchanger;

    private List<T> holder;

    ExchangerProducer(Exchanger<List<T>> exchanger,Generator<T> gen, List<T> holder) {
        this.exchanger = exchanger;
        this.generator = gen;
        this.holder = holder;
    }


    public void run() {
         while (!Thread.interrupted()) {
             for(int i=0; i < 10;i ++) {
                 holder.add(generator.next());
                 System.out.println("producer " +  generator.next());
             }
             try {
                 //交换对象
                 holder = exchanger.exchange(holder);
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
    }
}
