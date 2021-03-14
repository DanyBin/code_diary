package concurrency.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @ClassName ExchangerConsumer
 * @Author bin
 * @Date 2020/8/21 下午8:26
 * @Decr TODO
 * @Link TODO
 **/
public class ExchangerConsumer<T> implements Runnable {

    private Exchanger<List<T>> exchanger;
    private List<T> holder;
    private volatile T value;

    ExchangerConsumer(Exchanger<List<T>> ex, List<T> holder) {
        exchanger = ex;
        this.holder = holder;
    }


    public void run() {
        try {
            while (!Thread.interrupted()) {
                holder = exchanger.exchange(holder);
                for(T x : holder) {
                    value = x;
                    holder.remove(x);
                    System.out.println("consumer " + x);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final value : " + value);
    }


}
