package rateLimit;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.Semaphore;
import java.util.function.Function;

/**
 * @ClassName ObjPool
 * @Author bin
 * @Date 2019/12/9 下午8:26
 * @Decr TODO
 *      实现对象池
 *      限流- 同时满足5个线程，访问对象，超出线程则等待。
 *
 *      使用信号量的方式实现.
 *
 * @Link TODO
 **/
public class ObjPool<T,R> {


    //对象池
    final List<T> pool;


    //初始化信号量
    final Semaphore sem;

    ObjPool(T[] tArray){

        //初始化对应池大小
        pool = new Vector<T>(){};

        for(int i=0;i< tArray.length;i++){
            pool.add(tArray[i]);
        }

        //设置信号量的 「计数器」
        sem = new Semaphore(tArray.length);
    }



    R exec(Function<T,R> func) throws InterruptedException{

        T t = null;

        //信号量 = 互斥
        sem.acquire();
        try {
            //获取对象
            t = pool.remove(0);
            return func.apply(t);
        }finally {
            pool.add(t);

            //信号量 == 同步
            sem.release();
        }
    }




    public static void main(String[] args)  throws  InterruptedException{
        String[] messages = new String[10];
        for(int i=0; i< 10 ; i++){
            messages[i] = "obj_"+i;
        }

        ObjPool<String, Object> pools = new ObjPool<>(messages);

        for(int i=0;i < 100 ; i++ ){
            new Thread(()->{
                try {
                    pools.exec(t->{
                        System.out.println("当前线程:"+Thread.currentThread().getId() + "当前获取对象"+t);
                        return  t;
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }).start();
        }
    }

}
