package guardedSuspension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Predicate;

/**
 * @ClassName GuardedObject
 * @Author bin
 * @Date 2020/1/20 上午11:26
 * @Decr TODO
 *      Guarded 方式- 等待唤醒机制。能够实现多线程下的等待获取结果
 * @Link TODO
 **/
public class GuardedObject<T> {
    //受保护对象
    T obj;


    final Lock lock = new ReentrantLock();
    final Condition done = lock.newCondition();
    final int timeout = 2;

    //保存所有的Guarded-Object
    final static Map<Object,GuardedObject> gos = new ConcurrentHashMap<>();

    //静态方法创建 GuardedObject
    static <K> GuardedObject  create(K key){
        GuardedObject go = new GuardedObject();
        gos.put(key,go);
        return go;
    }

    //遍历映射关系
    static <K,T> void fireEvent(K key,T obj){
        GuardedObject go = gos.remove(key);
        if(go != null){

        }
    }

    //获取受保护对象
    T get(Predicate<T> p){
        lock.lock();
        try {
            while (!p.test(obj)){
                done.await(timeout, TimeUnit.SECONDS);
            }
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }finally {
            lock.unlock();
        }
        //返回非空受保护对象
        return obj;
    }

    //事件通知方法
    void onChange(T obj){
        lock.lock();
        try{
            this.obj = obj;
            done.signalAll();
        }finally {
            lock.unlock();
        }
    }
}
