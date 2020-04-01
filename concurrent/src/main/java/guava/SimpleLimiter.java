package guava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName SimpleLimiter
 * @Author bin
 * @Date 2020/2/27 上午11:22
 * @Decr TODO
 *      关键:
 *          1. 记录下一个令牌产生的时间，并动态更新它
 *          2.
 *
 * @Link TODO
 **/
public class SimpleLimiter {

    static Long prev = 0L;


    //当前令牌桶中的令牌数量
    long storedPermits = 0;

    //令牌桶的容器
    long  maxPermits = 3;


    //下一个令牌产生时间
    long next = System.nanoTime();

    //发放令牌间隔: 纳秒
    long interval = 1000_000_000;


    //请求时间在下一令牌产生时间之后，则
    //1. 重新计算 - 令牌桶中的令牌数
    //2. 将下一个令牌发放时间重置为当前时间
    void resync(long now){
        if(now > next){
            //新产生的令牌数
            long newPermits = (now - next) / interval;

            //新令牌增加到令牌桶
            storedPermits = Math.min(maxPermits,storedPermits+newPermits);

            //将下一个令牌发放时间重置为当前时间
            next = now;
        }
    }

    //预占令牌，返回能够获取令牌的时间
    synchronized long reserve(long now){

        resync(now);

        //能够获取令牌的时间
        long at = next;

        //令牌桶中能提供的令牌
        long fb = Math.min(1,storedPermits);


        //令牌净需求： 首先剪掉令牌桶中的令牌
        long nr = 1 - fb;


        //重新计算令牌产生时间
        next = next + nr*interval;

        //重新计算令牌桶中的令牌
        this.storedPermits -=fb;

        return at;
    }

    //申请令牌
    void acquire(){
        //申请令牌的时间
        long now = System.nanoTime();

        //预占令牌
        long at = reserve(now);
        long waitTime = Math.max(at-now,0);

        if(waitTime > 0){
            try {
                TimeUnit.NANOSECONDS.sleep(waitTime);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SimpleLimiter simpleLimiter = new SimpleLimiter();
        int s = 67568806;


        //执行任务的线程池
        ExecutorService es = Executors.newFixedThreadPool(10);


        prev= System.nanoTime();
        for(int i=0;i < 20; i++){
            simpleLimiter.acquire();
            es.execute(()->{
                long cur = System.nanoTime();
                System.out.println((cur-prev)/1000_000);
                prev = cur;
            });
        }
    }
}
