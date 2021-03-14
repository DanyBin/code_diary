package concurrency.test;

/**
 * @ClassName SynchronizedTest
 * @Author bin
 * @Date 2020/8/25 下午5:00
 * @Decr TODO
 * @Link TODO
 **/
public class SynchronizedTest extends Accumulator {
    {
        id = "synchronized";
    }

    @Override
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if(index > SIZE) index = 0;
    }

    @Override
    public synchronized long read() {
        return value;
    }
}
