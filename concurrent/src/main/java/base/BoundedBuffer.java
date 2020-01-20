package base;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName BoundedBuffer
 * @Author bin
 * @Date 2019/12/3 下午7:43
 * @Decr TODO
 *      有界缓冲区
 *          用于测试 「Monitor」
 * @Link TODO
 **/
public class BoundedBuffer {


    //锁对象
   final Lock lock =  new ReentrantLock();


   //写线程条件
   final Condition notFull = lock.newCondition();

   //读线程条件
   final Condition notEmpty = lock.newCondition();

   //缓存队列
   final int[] items = new int[100];

   //写索引
   int head;

   //读索引
   int tail;

   //队列中存在的数据个数
    int count;

    public static void main(String[] args) {

        BoundedBuffer boundedBuffer = new BoundedBuffer();

        int x = 10;
        for(int i=1;i < 1001;i ++ ){
            new Thread(()->{
                try {
                    boundedBuffer.put(x+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            new Thread(()->{
                try {
                    System.out.println(boundedBuffer.take());
                    boundedBuffer.put(boundedBuffer.take()+1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * 入队操作
     * @param x
     * @throws InterruptedException
     */
    public void put(int x) throws InterruptedException{

        lock.lock();

        try {
            //如果队列满了
            while (count == items.length){
                //阻塞写线程
                notFull.await();
            }

            items[tail] = x;

            if(++ tail == items.length){
                tail = 0;
            }

            count ++;
            System.out.println("新增数据:" +x);

            //唤醒读线程
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException{

        lock.lock();

        try {
            //如果队列为空
            while (count == 0){
                //阻塞读线程
                notEmpty.await();
            }
            int item = items[head];

            if(++ head == items.length){
                head =0;
            }
            --count;
            //唤醒写线程
            notFull.signal();
            return item;
        } finally {
            lock.unlock();
        }
    }

}

