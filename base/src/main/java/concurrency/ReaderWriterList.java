package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName ReaderWriterList
 * @Author bin
 * @Date 2020/8/25 下午7:34
 * @Decr TODO
 *      测试 ReadWriteLock的数据结构
 * @Link TODO
 **/
public class ReaderWriterList<T> {
    private ArrayList<T> lockedList;
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReaderWriterList(int size ,T initialValue) {
        lockedList = new ArrayList<T>(Collections.nCopies(size,initialValue));
    }

    public T set(int index,T element) {
        //写锁
        Lock wlock = lock.writeLock();
        wlock.lock();

        try {
            return lockedList.set(index,element);
        }finally {
            wlock.unlock();
        }
    }

    public T get(int index) {
        //读锁
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            //查询读锁的数量
            if(lock.getReadLockCount()  > 1) {
                System.out.println(lock.getReadLockCount());
            }
            return lockedList.get(index);
        }finally {
            rlock.unlock();
        }
    }

    public static void main(String[] args) {
        new ReaderWriterListTest(30,1);
    }
}
