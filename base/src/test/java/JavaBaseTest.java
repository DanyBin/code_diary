import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName JavaBaseTest
 * @Author bin
 * @Date 2021/3/6 上午10:29
 * @Decr TODO
 * @Link TODO
 **/
public class JavaBaseTest {

    @org.junit.Test
    public void  test1(){

        long start = System.currentTimeMillis();
        int max = 10000000;
        String[] str = new String[max];
        int random = new Random().nextInt(max);
        for (int i=0; i < max; i ++) {
            str[i] = new String(String.valueOf(i*random));
        }
        new ReentrantLock();

    }
}
