package concurrency.example.restaurant;

import java.util.concurrent.SynchronousQueue;

/**
 * @ClassName Customer
 * @Author bin
 * @Date 2020/8/24 下午7:03
 * @Decr TODO
 *      顾客
 * @Link TODO
 **/
public class Customer implements Runnable{
    private static int counter = 0;
    private final int id = counter ++;
    private final WaitPerson waitPerson;


    //每个盘子只能放一道菜
    private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<Plate>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    public void run() {

    }
}
