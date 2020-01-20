package future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName FutureTest
 * @Author bin
 * @Date 2020/1/8 下午7:08
 * @Decr TODO
 * @Link TODO
 **/
public class FutureTest {

    class T1Task implements Callable<String>{
        FutureTask<String> ft2;
        public T1Task(FutureTask<String> task){
            this.ft2 = task;
        }

        @Override
        public String call() throws Exception {
            System.out.println("T1  洗水壶");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T1 烧开水");

            String tf = ft2.get();

            System.out.println("T1 拿到茶叶" + tf);

            System.out.println("T1 泡茶");
            return "上茶";
        }
    }

    class T2Task implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("T2 洗茶壶");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2 洗茶杯");
            TimeUnit.SECONDS.sleep(1);

            System.out.println("T2 拿茶叶");
            TimeUnit.SECONDS.sleep(1);
            return "茉莉花";
        }
    }

    public static void main(String[] args) throws Exception {
        FutureTest futureTest = new FutureTest();
        FutureTask<String> ft2 = new FutureTask<>(futureTest.new T2Task());
        FutureTask<String> ft1 = new FutureTask<>(futureTest.new T1Task(ft2));

        Thread t1 = new Thread(ft1);
        t1.start();

        Thread t2 = new Thread(ft2);
        t2.start();

        System.out.println(ft1.get());
    }
}
