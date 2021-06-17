package cache;

import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName GuavaLoadingCache
 * @Author bin
 * @Date 2021/3/29 下午6:40
 * @Decr TODO
 * @Link TODO
 **/
public class GuavaLoadingCache {
    public static void main(String[] args) {


        //创建线程池
        ListeningExecutorService backBase = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(20));

        LoadingCache<String,Optional<List<String>>> loadingCache = CacheBuilder
                .newBuilder()
                //.expireAfterAccess(3, TimeUnit.SECONDS) //设置过期时间，高并发时，容易阻塞
                .refreshAfterWrite(1000,TimeUnit.SECONDS) //定时刷新，防止key过期
                .removalListener(listener -> System.out.println("cache expired, remove key : " + listener.getKey()))
                .build(new CacheLoader<String, Optional<List<String>>>() {
                    @Override
                    public Optional<List<String>> load(String key) throws Exception {
                        return Optional.of(MockDB.getCity(key));
                    }

                    //
                    @Override
                    public ListenableFuture<Optional<List<String>>> reload(String key, Optional<List<String>> oldValue) throws Exception {
                        return backBase.submit(new Callable<Optional<List<String>>>() {
                            @Override
                            public Optional<List<String>> call() throws Exception {
                                System.out.println(Thread.currentThread().getName()+"定时异步去更新");
                                return null;
                            }
                        });
                    }
                });


        try {
            System.out.println("load from cache once : " + loadingCache.get("0101").or(Lists.newArrayList()));
            Thread.sleep(2000);
            System.out.println("load from cache two : " + loadingCache.get("0101").or(Lists.newArrayList()));
            Thread.sleep(2000);
            System.out.println("load from cache three : " + loadingCache.get("0101").or(Lists.newArrayList()));
            Thread.sleep(2000);
            System.out.println("load not exist key from cache : " + loadingCache.get("0103").or(Lists.newArrayList()));

        } catch (ExecutionException | InterruptedException e) {
            //记录日志
        }
    }
}
