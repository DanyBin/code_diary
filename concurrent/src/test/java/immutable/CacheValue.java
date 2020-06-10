package immutable;

import org.junit.Test;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName CacheValue
 * @Author bin
 * @Date 2020/4/2 上午11:24
 * @Decr TODO
 * @Link TODO
 **/
public class CacheValue {
    //创建不可变对象的引用
    private volatile OneValueCache cache = new OneValueCache(null,new BigInteger[]{});
    private AtomicInteger count = new AtomicInteger(0);

    public void cacheFun(BigInteger integer){
        BigInteger[] lastFactory = cache.getLastFactory(integer);
        if(lastFactory == null){
            lastFactory = new BigInteger[]{integer};
            cache = new OneValueCache(integer,lastFactory);
            System.out.println(String.format("cache thread name : %s",Thread.currentThread().getName()));
        }else {
            count.getAndIncrement();
        }


    }

    @Test
    public void test() throws InterruptedException{
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i < 100;i++){
            executorService.submit(()->{
                BigInteger integer = new BigInteger("1234567890");
                cacheFun(integer);
            });
        }
        executorService.shutdown();
        System.out.println(String.format("no cache count: %s",count.get()));
        System.out.println(String.format("cache result length:%s",cache.getLastFactory(new BigInteger("1234567890")).length));
    }

}
