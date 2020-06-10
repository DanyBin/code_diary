package thredSynchronize.build;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName Memoizer2
 * @Author bin
 * @Date 2020/5/10 下午4:28
 * @Decr TODO
 *      支持多线程并发访问，但当了两个线程可能会计算相同的值。重复计算
 * @Link TODO
 **/
public class Memoizer2<A,V> implements Computable<A,V> {
    //使用并发容器缓存
    private final Map<A,V> cache = new ConcurrentHashMap<>();
    //计算
    private final Computable<A,V> c;


    public Memoizer2(Computable<A,V> c){
        this.c =c;
    }
    @Override
    public V compute(A arg) throws InterruptedException {
        V v = cache.get(arg);
        //若没有缓存，在计算否则，返回结果
        if(v == null){
            v = c.compute(arg);
            cache.put(arg,v);
        }
        return v;
    }
}
