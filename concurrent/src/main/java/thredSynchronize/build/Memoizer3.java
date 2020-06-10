package thredSynchronize.build;

import java.util.Map;
import java.util.concurrent.*;

/**
 * @ClassName Memoizer3
 * @Author bin
 * @Date 2020/5/10 下午4:30
 * @Decr TODO
 *
 * @Link TODO
 **/
public class Memoizer3<A,V> implements Computable<A,V> {
    //使用并发容器缓存. value = Future
    private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
    //计算
    private final Computable<A,V> c;


    public Memoizer3(Computable<A,V> c){
        this.c =c;
    }
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> vFuture = cache.get(arg);
        if(vFuture == null){

            //创建线程开始计算
            Callable<V> eval = new Callable<V>() {

                @Override
                public V call() throws Exception {
                    return c.compute(arg);
                }
            };

            //启动线程并注册到缓存中
            FutureTask<V> vFutureTask = new FutureTask<>(eval);
            vFuture = vFutureTask;
            //使用原子方法
            cache.putIfAbsent(arg,vFuture);
            vFutureTask.run();
        }
        try {
            return vFuture.get();
        } catch (ExecutionException e) {

        }
        return null;
    }

}
