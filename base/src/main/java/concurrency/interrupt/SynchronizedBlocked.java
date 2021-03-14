package concurrency.interrupt;

/**
 * @ClassName SynchronizedBlocked
 * @Author bin
 * @Date 2020/8/14 上午11:15
 * @Decr TODO
 *      不可中断
 * @Link TODO
 **/
public class SynchronizedBlocked implements Runnable {


    public synchronized void f(){
        while (true)
            Thread.yield();
    }


    public SynchronizedBlocked(){
        new Thread() {
            public void run() {
                f();
            }
        }.start();
    }

    public void run() {
        System.out.println("try to call f()");
        f();
        System.out.println("exit SynchronizedBlocked");
    }
}
