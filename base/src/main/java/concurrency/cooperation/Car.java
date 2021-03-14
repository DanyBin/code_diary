package concurrency.cooperation;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName Car
 * @Author bin
 * @Date 2020/8/18 下午5:37
 * @Decr TODO
 *      蜡涂
 *      抛光
 *
 * @Link TODO
 **/
public class Car {


    private Lock  lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean waxOn =false;

    //蜡涂
    public  void waxed(){
        lock.lock();
        try {
            waxOn = true; //准备抛光
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    //抛光
    public  void buffed(){
        lock.lock();
        try {
            waxOn = false; //准备蜡涂'
            condition.signalAll();
        }finally {
            lock.unlock();
        }

    }

    //等待蜡涂
    public  void  waitForWaxing() throws InterruptedException{
        lock.lock();
        try {
            while (!waxOn) {
                condition.await();
            }
        }finally {
            lock.unlock();
        }

    }

    //等待抛光
    public  void waitForBuffing() throws InterruptedException{
        lock.lock();
        try {
            while (waxOn){
               condition.await();
            }
        }finally {
            lock.unlock();
        }

    }
}
