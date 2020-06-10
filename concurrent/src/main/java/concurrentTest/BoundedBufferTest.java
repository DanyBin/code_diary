package concurrentTest;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @ClassName BoundedBufferTest
 * @Author bin
 * @Date 2020/6/5 下午3:48
 * @Decr TODO
 * @Link TODO
 **/
public class BoundedBufferTest {

    @Test
    public void isEmtry(){
        BoundedBuffer<Integer> integerBoundedBuffer = new BoundedBuffer<>(10);

        Assert.assertTrue(integerBoundedBuffer.isEmpty());
        Assert.assertFalse(integerBoundedBuffer.isFull());
    }

    @Test
    public void isFull() throws InterruptedException{
        BoundedBuffer<Integer> integerBoundedBuffer = new BoundedBuffer<>(10);
        for(int i=0; i  < 10 ;i ++){
            integerBoundedBuffer.put(i);
        }
        Assert.assertTrue(integerBoundedBuffer.isFull());
        Assert.assertFalse(integerBoundedBuffer.isEmpty());
    }

    @Test
    public void testTakeBlocks(){
        BoundedBuffer<Integer> integerBoundedBuffer = new BoundedBuffer<>(10);
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Integer take = integerBoundedBuffer.take();
                    fail();
                } catch (InterruptedException success) {
                }
            }
        };

        try {
            thread.start();
            Thread.sleep(1000L);
            thread.interrupt();
            thread.join(1000L);
            Assert.assertFalse(thread.isAlive());
        } catch (InterruptedException e) {
            fail();
        }
    }
}
