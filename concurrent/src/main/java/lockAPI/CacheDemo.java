package lockAPI;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.*;

/**
 * @ClassName CacheDemo
 * @Author bin
 * @Date 2019/12/11 下午3:39
 * @Decr TODO
 *      使用读写锁-实现缓存功能
 *
 * @Link TODO
 **/
public class CacheDemo {

   final Map<String,Integer> map = new HashMap<>();


   //读写Lock
   final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

   //读锁
   final Lock r  = readWriteLock.readLock();

    //写锁
    final Lock w = readWriteLock.writeLock();



    //读缓存
    public Integer get(String key){
        Integer v = null;
        r.lock();
        try {
            v = map.get(key);
        } finally {
            r.unlock();
        }
        if(v != null){
            return v;
        }

        //缓存不存在
        w.lock();
        try {
            System.out.println(111);
            v = map.get(key);
            if(v == null){
                v = 15;
                this.map.put(key,15);
            }
        } finally {
            w.unlock();
        }
        return v;
    }



    //写缓存
    void put(String key,Integer value){
        w.lock();
        try {
            map.put(key,value);
        } finally {
            w.unlock();
        }
    }

    public static void main(String[] args) {
        CacheDemo cacheDemo = new CacheDemo();

        //初始化数据
        for(int i=0; i < 10 ;i ++){
                cacheDemo.map.put("key_"+i,i);
        }

        for(int i=0;i < 100;i++){
            new Thread(cacheDemo.new ThreadDemo("key_" + 10,cacheDemo)).start();

        }
    }


    class ThreadDemo implements Runnable{

        private final String key;
        private final CacheDemo cacheDemo;

        ThreadDemo(String key,CacheDemo cacheDemo){
            this.key = key;
            this.cacheDemo = cacheDemo;
        }
        @Override
        public void run() {
            System.out.println("当前线程id="+Thread.currentThread().getId()+"   当前key的值="+this.key + " value="+cacheDemo.get(this.key));
        }
    }
}
