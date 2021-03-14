package concurrency.test;

import concurrency.atomic.AtomicIntegerTest;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @ClassName AtomicTest
 * @Author bin
 * @Date 2020/8/25 下午5:06
 * @Decr TODO
 * @Link TODO
 **/
public class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);
    public void accumulate() {
        int i = index.getAndIncrement();
        value.getAndAdd(i);

        if(i++ >= SIZE) {
            index.set(0);
        }
    }

    public long read() {
        return value.get();
    }
}
