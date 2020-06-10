package concurrentTest;

import java.util.concurrent.Semaphore;

/**
 * @ClassName BoundedBuffer
 * @Author bin
 * @Date 2020/6/5 下午3:39
 * @Decr TODO
 *      缓存实现
 * @Link TODO
 **/
public class BoundedBuffer<E> {

    // availablelItems = 元素个数 availableSpaces=数组空间长度
    private final Semaphore  availablelItems,availableSpaces;

    //数组
    private final E[] items;
    //put的索引位置、take的索引位置
    private int putPosition= 0,takePosition = 0;



    public BoundedBuffer(int capacity){
        availablelItems = new Semaphore(0);
        availableSpaces = new Semaphore(capacity);
        items = (E[]) new Object[capacity];
    }

    public boolean isEmpty(){
        return availablelItems.availablePermits() == 0;
    }

    public boolean isFull(){
        return availableSpaces.availablePermits() == 0;
    }

    public void put(E x) throws  InterruptedException{
        availableSpaces.acquire();
        doInset(x);
        availablelItems.release();
    }

    public E take () throws InterruptedException{
        availablelItems.acquire();
        E item = doExtract();
        availableSpaces.release();
        return item;
    }

    private synchronized void doInset(E x){
        int i = putPosition;
        items[i] = x;
        putPosition = (++i == items.length)?0 :i;
    }

    private synchronized E doExtract(){
        int i = takePosition;
        E x = items[i];
        items[i] = null;
        takePosition = (++i == items.length) ? 0:i;
        return x;
    }
}
