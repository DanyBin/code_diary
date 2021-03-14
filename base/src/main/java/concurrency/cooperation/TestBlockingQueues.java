package concurrency.cooperation;

import concurrency.LiftOff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @ClassName TestBlockingQueues
 * @Author bin
 * @Date 2020/8/19 下午3:10
 * @Decr TODO
 * @Link TODO
 **/
public class TestBlockingQueues {

    static void getKey() {
        try {
            new BufferedReader(
                    new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    static void getKey(String message) {
        System.out.println(message);
        getKey();
    }

    static void test(String msg, BlockingQueue<LiftOff> queue) {
        System.out.println(msg);
        LiftOffRunner runner = new LiftOffRunner(queue);
        Thread t = new Thread(runner);

        for(int i=0;i < 5 ;i ++){
            runner.add(new LiftOff(5));
        }
        getKey("Pree enter ：" + msg);
        t.interrupt();
        System.out.println("finshed " + msg + " test");
    }

    public static void main(String[] args) {
        test("LinkedBlockingQueue",new LinkedBlockingQueue<LiftOff>());
    }
}
