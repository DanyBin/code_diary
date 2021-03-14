package jvm.gc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName JConsole_TestCase
 * @Author bin
 * @Date 2020/11/5 下午4:48
 * @Decr TODO
 *      使用JConsole监控jvm
 *
 *      JVM = -Xms100m -Xmx100m -XX:+UseSerialGC
 * @Link TODO
 **/
public class JConsole_TestCase {


    /**
     * 内存监控测试
     */
    static class  OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject>  list = new ArrayList<OOMObject>();
        for (int i=0;i < num; i++){
            Thread.sleep(50);
            list.add(new OOMObject());
        }

        System.gc();
    }


    /**
     * 线程死循环演示
     */
    public static void createBusyThread(){
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (true);
            }
        },"testBusyThread");
        thread.start();
    }


    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"testLockThread");
        thread.start();
    }



    static class SynAddRunable implements Runnable{
        int a,b;

        public SynAddRunable(int a,int b) {
            this.a = a;
            this.b = b;
        }


        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        //fillHeap(1000);
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br.readLine();
//        createBusyThread();
//        br.readLine();
//        Object obj = new Object();
//        createLockThread(obj);

        for(int i=0; i < 100 ; i++) {
            new Thread(new SynAddRunable(1,2)).start();
            new Thread(new SynAddRunable(2,1)).start();
        }

    }
}
