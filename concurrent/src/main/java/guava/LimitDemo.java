package guava;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName LimitDemo
 * @Author bin
 * @Date 2020/2/27 上午11:06
 * @Decr TODO
 *      简单使用guava的限流器
 * @Link TODO
 **/
public class LimitDemo {

    static Long prev = 0L;
    public static void main(String[] args) {
        //限流器， 2个请求/秒
        RateLimiter limiter = RateLimiter.create(2.0);

        //执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(1);

        //记录上一次的执行时间
        prev = System.nanoTime();

        for(int i=0;i < 20 ; i++){
            //限流器 限流
            limiter.acquire();
            //提供异步执行任务
            es.execute(()->{
                long cur = System.nanoTime();
                System.out.println((cur-prev)/1000_000);
                prev = cur;
            });
        }
    }
}
