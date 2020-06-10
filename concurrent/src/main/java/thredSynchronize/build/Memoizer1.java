package thredSynchronize.build;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Memoizer1
 * @Author bin
 * @Date 2020/5/10 下午4:23
 * @Decr TODO
 *      问题: 对compute方法上锁，仅能单线程访问，容易发生阻塞
 * @Link TODO
 **/
public class Memoizer1<A,V> implements Computable<A,V> {

    //缓存
    private final Map<A,V> cache = new HashMap<>();
    //计算
    private final Computable<A,V> c;


    public Memoizer1(Computable<A,V> c){
        this.c =c;
    }
    @Override
    public synchronized V compute(A arg) throws InterruptedException {
        V v = cache.get(arg);
        //若没有缓存，在计算否则，返回结果
        if(v == null){
            v = c.compute(arg);
            cache.put(arg,v);
        }
        return v;
    }
}
