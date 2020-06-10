import org.junit.Test;

import java.sql.Connection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName DemoThreadLocal
 * @Author bin
 * @Date 2020/4/1 上午11:41
 * @Decr TODO
 * @Link TODO
 **/
public class DemoThreadLocal {




    //给每个线程分配一个原子ID
    private static final AtomicInteger nextId = new AtomicInteger(0);

    //包含每个线程id的线程局部变量
    private final static ThreadLocal<Integer> threadId = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    //返回当前线程的唯一ID，并在必要时分配它
    public static int get(){
        return threadId.get();
    }

    @Test
    public void thread() throws InterruptedException{

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for(int i=0;i< 1000;i++){
            executorService.submit(()->{
                System.out.println(String.format("thread-name: %s thread-id: %s" ,Thread.currentThread().getName(),get()));
                /*Thread.setDefaultUncaughtExceptionHandler();*/
            });
        }
        executorService.shutdown();
    }
}
