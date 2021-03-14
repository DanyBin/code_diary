package concurrency.exception;

/**
 * @ClassName ExceptionThread
 * @Author bin
 * @Date 2020/8/11 下午3:32
 * @Decr TODO
 * @Link TODO
 **/
public class ExceptionThread implements Runnable {
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}
