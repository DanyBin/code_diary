package threadFactory;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestingThreadFactory
 * @Author bin
 * @Date 2020/6/5 下午5:14
 * @Decr TODO
 * @Link TODO
 **/
public class TestingThreadFactory implements ThreadFactory {

    public final AtomicInteger numCreated = new AtomicInteger();

    private final ThreadFactory factory = Executors.defaultThreadFactory();

    @Override
    public Thread newThread(Runnable r) {
        numCreated.incrementAndGet();
        return factory.newThread(r);
    }

    @Test
    public void testPoolExpansion() throws  InterruptedException{
        int size = 10;
        TestingThreadFactory testingThreadFactory = new TestingThreadFactory();
        ExecutorService exec = Executors.newFixedThreadPool(size,testingThreadFactory);


        for(int i=0;i < 10*size;i ++){
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.MAX_VALUE);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
        }

        for (int i=0; i< 20*size && testingThreadFactory.numCreated.get() < size; i++){
            Thread.sleep(100);
        }
        System.out.println(testingThreadFactory.numCreated.get());
        Assert.assertEquals(testingThreadFactory.numCreated.get(),size);
        exec.shutdown();
    }
}
